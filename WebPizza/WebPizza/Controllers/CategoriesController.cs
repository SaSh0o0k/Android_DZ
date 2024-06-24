using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebPizza.Data;

namespace WebPizza.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CategoriesController : ControllerBase
    {
        private readonly PizzaDbContext _pizzaContext;

        public CategoriesController(PizzaDbContext pizzaContext)
        {
            _pizzaContext = pizzaContext;
        }

        [HttpGet]
        public IActionResult Get()
        {
            var list = _pizzaContext.Categories.ToList();
            return Ok(list);
        }
    }
}