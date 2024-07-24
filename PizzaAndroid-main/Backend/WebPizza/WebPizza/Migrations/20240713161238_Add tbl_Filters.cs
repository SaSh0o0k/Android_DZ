using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace WebPizza.Migrations
{
    /// <inheritdoc />
    public partial class Addtbl_Filters : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "tbl_filters",
                columns: table => new
                {
                    FilterValueId = table.Column<int>(type: "integer", nullable: false),
                    PizzaId = table.Column<int>(type: "integer", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_tbl_filters", x => new { x.FilterValueId, x.PizzaId });
                    table.ForeignKey(
                        name: "FK_tbl_filters_tbl_filterValues_FilterValueId",
                        column: x => x.FilterValueId,
                        principalTable: "tbl_filterValues",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_tbl_filters_tbl_pizzas_PizzaId",
                        column: x => x.PizzaId,
                        principalTable: "tbl_pizzas",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_tbl_filters_PizzaId",
                table: "tbl_filters",
                column: "PizzaId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "tbl_filters");
        }
    }
}
