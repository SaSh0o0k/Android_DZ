using AutoMapper;
using WebPizza.Data.Entities;
using WebPizza.Data;
using WebPizza.Services.PaginationServices.Base;
using WebPizza.ViewModels.Category;

namespace WebPizza.Services.PaginationServices;

public class CategoryPaginationService(
    PizzaDbContext context,
    IMapper mapper
) : PaginationService<CategoryEntity, CategoryVm, CategoryFilterVm>(mapper)
{
    protected override IQueryable<CategoryEntity> GetQuery() => context.Categories.OrderBy(c => c.Id);

    protected override IQueryable<CategoryEntity> FilterQuery(IQueryable<CategoryEntity> query, CategoryFilterVm paginationVm)
    {
        if (paginationVm.Name is not null)
            query = query.Where(c => c.Name.ToLower().Contains(paginationVm.Name.ToLower()));

        return query;
    }
}