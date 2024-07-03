namespace WebPizza.ViewModels.Category;

public class CategoryCreateVM
{
    public string Name { get; set; } = null!;

    public IFormFile Image { get; set; } = null!;
}