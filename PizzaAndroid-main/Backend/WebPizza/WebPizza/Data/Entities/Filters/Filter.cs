using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Diagnostics.Contracts;

namespace WebPizza.Data.Entities.Filters
{
    [Table("tbl_filters")]
    public class Filter
    {
        [ForeignKey("FilterValue")]
        public int FilterValueId { get; set; }

        public virtual FilterValue FilterValue { get; set; } = null!;

        [ForeignKey("Pizza")]
        public int PizzaId { get; set; }

        public virtual PizzaEntity Pizza { get; set; } = null!;
    }

}

