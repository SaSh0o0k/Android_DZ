using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Diagnostics.Contracts;

namespace WebPizza.Data.Entities.Filters
{
    [Table("tbl_filterNames")]
    public class FilterName:BaseEntity
    {
        [StringLength(255), Required]
        public string Name { get; set; } = null!;

        [ForeignKey("Category")]
        public int CategoryId { get; set; }
        public virtual CategoryEntity Category { get; set; } = null!;
    }
}

