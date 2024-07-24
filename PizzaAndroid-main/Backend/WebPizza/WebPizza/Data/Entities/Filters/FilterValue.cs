using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Diagnostics.Contracts;

namespace WebPizza.Data.Entities.Filters
{
    [Table("tbl_filterValues")]
    public class FilterValue:BaseEntity
    {
        [StringLength(255), Required]
        public string Name { get; set; } = null!;

        [ForeignKey("FilterName")]
        public int FilterNameId { get; set; }
        public virtual FilterName FilterName { get; set; } = null!;

        public virtual ICollection<FilterValue> FilterValues { get; set; }=null!;

        public ICollection<Filter> Filters { get; set; } = new List<Filter>();
    }
}

