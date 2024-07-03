using FluentValidation;
using WebPizza.Interfaces;
using WebPizza.ViewModels.Category;

namespace WebPizza.Validators.Category;
public class CategoryCreateValidator : AbstractValidator<CategoryCreateVM>
{
    public CategoryCreateValidator(IImageValidator imageValidator)
    {
        RuleFor(c => c.Name)
            .NotEmpty()
                .WithMessage("Name is empty or null")
            .MaximumLength(255)
                .WithMessage("Name is too long");

        RuleFor(c => c.Image)
            .NotNull()
                .WithMessage("Image is not selected")
            .MustAsync(imageValidator.IsValidImageAsync)
                .WithMessage("Image is not valid");
    }
}