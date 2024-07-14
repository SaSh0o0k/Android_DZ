using Bogus;
using Microsoft.EntityFrameworkCore;
using WebPizza.Data.Entities;
using WebPizza.Interfaces;

namespace WebPizza.Data
{
    public static class SeederDB
    {
        public static void SeedData(this IApplicationBuilder app)
        {
            using (var scope = app.ApplicationServices
                .GetRequiredService<IServiceScopeFactory>().CreateScope())
            {
                var context = scope.ServiceProvider.GetRequiredService<PizzaDbContext>();
                var imageService = scope.ServiceProvider.GetService<IImageService>();
                var configuration = scope.ServiceProvider.GetService<IConfiguration>(); 
                context.Database.Migrate();

                using var httpClient = new HttpClient();

                if (!context.Categories.Any())
                {
                    var supy = new CategoryEntity
                    {
                        Name = "Супи",
                        Image = "supy.webp"
                    };
                    var pizza = new CategoryEntity
                    {
                        Name = "Піца",
                        Image = "pizza.webp"
                    };
                    var sushi = new CategoryEntity
                    {
                        Name = "Суші",
                        Image = "sushi.webp"
                    };

                    context.Categories.Add(supy);
                    context.Categories.Add(pizza);
                    context.Categories.Add(sushi);
                    context.SaveChanges();
                }

                // Ingredient seed
                if (context.Ingredients.Count() < 1)
                {
                    Faker faker = new Faker();

                    var fakeIngredient = new Faker<IngredientEntity>("uk")
                        .RuleFor(o => o.DateCreated, f => DateTime.UtcNow.AddDays(f.Random.Int(-10, -1)))
                        .RuleFor(c => c.Name, f => f.Commerce.ProductMaterial());

                    var fakeIngredients = fakeIngredient.Generate(10);

                    foreach (var ingredient in fakeIngredients)
                    {
                        var imageUrl = faker.Image.LoremFlickrUrl(keywords: "fruit", width: 1000, height: 800);
                        var imageBase64 = GetImageAsBase64(httpClient, imageUrl);

                        ingredient.Image = imageService.SaveImageAsync(imageBase64).Result;
                    }

                    context.Ingredients.AddRange(fakeIngredients);
                    context.SaveChanges();
                }

                // Sizes seed
                if (context.Sizes.Count() < 1)
                {
                    var pizzaSizes = configuration
                        .GetSection("DefaultSeedData:PizzaSizes")
                        .Get<string[]>();

                    if (pizzaSizes is null)
                        throw new Exception("Configuration DefaultSeedData:PizzaSizes is invalid");

                    List<PizzaSizeEntity> sizes = new List<PizzaSizeEntity>();

                    foreach (var size in pizzaSizes)
                    {
                        sizes.Add(new PizzaSizeEntity { Name = size });
                    }

                    context.Sizes.AddRange(sizes);
                    context.SaveChanges();
                }

                // Pizza seed
                if (context.Pizzas.Count() < 1)
                {
                    var faker = new Faker("en");

                    var fakePizzas = new Faker<PizzaEntity>("uk")
                        .RuleFor(o => o.Name, f => f.Commerce.ProductName())
                        .RuleFor(o => o.Description, f => f.Lorem.Sentence())
                        .RuleFor(o => o.Rating, f => f.Random.Double(0, 5))
                        .RuleFor(o => o.IsAvailable, f => f.Random.Bool())
                        .RuleFor(o => o.CategoryId, f => f.PickRandom(context.Categories.Select(c => c.Id).ToList()));

                    var pizzas = fakePizzas.Generate(10);

                    foreach (var pizza in pizzas)
                    {
                        int numberOfPhotos = faker.Random.Int(1, 5);
                        for (int i = 0; i < numberOfPhotos; i++)
                        {
                            var imageUrl = faker.Image.LoremFlickrUrl(keywords: "pizza", width: 1000, height: 800);
                            var imageBase64 = GetImageAsBase64(httpClient, imageUrl);

                            pizza.Photos.Add(new PizzaPhotoEntity
                            {
                                Name = imageService.SaveImageAsync(imageBase64).Result,
                                Priority = i + 1
                            });
                        }

                        pizza.Ingredients = context.Ingredients
                            .OrderBy(i => Guid.NewGuid())
                            .Take(new Faker().Random.Int(1, 5))
                            .Select(i => new PizzaIngredientEntity { IngredientId = i.Id })
                            .ToList();

                        pizza.Sizes = context.Sizes
                            .OrderBy(i => Guid.NewGuid())
                            .Take(new Faker().Random.Int(1, 5))
                            .Select(s => new PizzaSizePriceEntity
                            {
                                SizeId = s.Id,
                                Price = faker.Random.Decimal(100, 400)
                            })
                            .ToList();
                    }

                    context.Pizzas.AddRange(pizzas);
                    context.SaveChanges();
                }

            }
        }
        private static string GetImageAsBase64(HttpClient httpClient, string imageUrl)
        {
            var imageBytes = httpClient.GetByteArrayAsync(imageUrl).Result;
            return Convert.ToBase64String(imageBytes);
        }
    }
}