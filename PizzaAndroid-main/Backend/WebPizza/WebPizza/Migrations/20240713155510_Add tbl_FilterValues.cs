using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace WebPizza.Migrations
{
    /// <inheritdoc />
    public partial class Addtbl_FilterValues : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "tbl_filterValues",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Name = table.Column<string>(type: "character varying(255)", maxLength: 255, nullable: false),
                    FilterNameId = table.Column<int>(type: "integer", nullable: false),
                    FilterValueId = table.Column<int>(type: "integer", nullable: true),
                    IsDeleted = table.Column<bool>(type: "boolean", nullable: false),
                    DateCreated = table.Column<DateTime>(type: "timestamp with time zone", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_tbl_filterValues", x => x.Id);
                    table.ForeignKey(
                        name: "FK_tbl_filterValues_tbl_filterNames_FilterNameId",
                        column: x => x.FilterNameId,
                        principalTable: "tbl_filterNames",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_tbl_filterValues_tbl_filterValues_FilterValueId",
                        column: x => x.FilterValueId,
                        principalTable: "tbl_filterValues",
                        principalColumn: "Id");
                });

            migrationBuilder.CreateIndex(
                name: "IX_tbl_filterValues_FilterNameId",
                table: "tbl_filterValues",
                column: "FilterNameId");

            migrationBuilder.CreateIndex(
                name: "IX_tbl_filterValues_FilterValueId",
                table: "tbl_filterValues",
                column: "FilterValueId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "tbl_filterValues");
        }
    }
}
