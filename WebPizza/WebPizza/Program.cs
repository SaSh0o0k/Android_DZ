using Microsoft.Extensions.FileProviders;
using Microsoft.EntityFrameworkCore;
using System;
using WebPizza.Data;
using WebPizza.Interfaces;
using WebPizza.Mapper;
using WebPizza.Services;
using FluentValidation;
using WebPizza.Services.ControllerServices.Interfaces;
using WebPizza.Services.ControllerServices;
using WebPizza.Services.PaginationServices;
using WebPizza.ViewModels.Category;
using WebPizza.ViewModels.Pizza;
using WebPizza.Validators.Pizza;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddDbContext<PizzaDbContext>(opt =>
    opt.UseNpgsql(builder.Configuration.GetConnectionString("Npgsql")));

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddValidatorsFromAssemblyContaining<Program>();

builder.Services.AddAutoMapper(typeof(AppMapProfile));
builder.Services.AddTransient<IImageService, ImageService>();
builder.Services.AddTransient<IImageValidator, ImageValidator>();

builder.Services.AddTransient<IExistingEntityCheckerService, ExistingEntityCheckerService>();

builder.Services.AddTransient<ICategoryControllerService, CategoryControllerService>();
builder.Services.AddTransient<IPaginationService<CategoryVm, CategoryFilterVm>, CategoryPaginationService>();

builder.Services.AddTransient<IIngredientControllerService, IngredientControllerService>();
builder.Services.AddTransient<IPizzaControllerService, PizzaControllerService>();
builder.Services.AddTransient<IPaginationService<PizzaVm, PizzaFilterVm>, PizzaPaginationService>();

builder.Services.AddCors();

var app = builder.Build();

app.UseCors(x => x.AllowAnyOrigin().AllowAnyHeader().AllowAnyMethod());

string imagesDirPath = Path.Combine(Directory.GetCurrentDirectory(), builder.Configuration["ImagesDir"]);

if (!Directory.Exists(imagesDirPath))
{
    Directory.CreateDirectory(imagesDirPath);
}

app.UseStaticFiles(new StaticFileOptions
{
    FileProvider = new PhysicalFileProvider(imagesDirPath),
    RequestPath = "/images"
});

// Configure the HTTP request pipeline.
//if (app.Environment.IsDevelopment())
//{
    app.UseSwagger();
    app.UseSwaggerUI();
//}

app.UseAuthorization();

app.MapControllers();

app.SeedData();

app.Run();
