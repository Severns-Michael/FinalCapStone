using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace PetTinder.Service.Migrations
{
    /// <inheritdoc />
    public partial class testing : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_UserTrait_Traits_traitId",
                table: "UserTrait");

            migrationBuilder.DropForeignKey(
                name: "FK_UserTrait_Users_userId",
                table: "UserTrait");

            migrationBuilder.DropPrimaryKey(
                name: "PK_UserTrait",
                table: "UserTrait");

            migrationBuilder.RenameTable(
                name: "UserTrait",
                newName: "UserTraits");

            migrationBuilder.RenameIndex(
                name: "IX_UserTrait_userId",
                table: "UserTraits",
                newName: "IX_UserTraits_userId");

            migrationBuilder.RenameIndex(
                name: "IX_UserTrait_traitId",
                table: "UserTraits",
                newName: "IX_UserTraits_traitId");

            migrationBuilder.AddPrimaryKey(
                name: "PK_UserTraits",
                table: "UserTraits",
                column: "userTraitId");

            migrationBuilder.AddForeignKey(
                name: "FK_UserTraits_Traits_traitId",
                table: "UserTraits",
                column: "traitId",
                principalTable: "Traits",
                principalColumn: "traitId",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserTraits_Users_userId",
                table: "UserTraits",
                column: "userId",
                principalTable: "Users",
                principalColumn: "userId",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_UserTraits_Traits_traitId",
                table: "UserTraits");

            migrationBuilder.DropForeignKey(
                name: "FK_UserTraits_Users_userId",
                table: "UserTraits");

            migrationBuilder.DropPrimaryKey(
                name: "PK_UserTraits",
                table: "UserTraits");

            migrationBuilder.RenameTable(
                name: "UserTraits",
                newName: "UserTrait");

            migrationBuilder.RenameIndex(
                name: "IX_UserTraits_userId",
                table: "UserTrait",
                newName: "IX_UserTrait_userId");

            migrationBuilder.RenameIndex(
                name: "IX_UserTraits_traitId",
                table: "UserTrait",
                newName: "IX_UserTrait_traitId");

            migrationBuilder.AddPrimaryKey(
                name: "PK_UserTrait",
                table: "UserTrait",
                column: "userTraitId");

            migrationBuilder.AddForeignKey(
                name: "FK_UserTrait_Traits_traitId",
                table: "UserTrait",
                column: "traitId",
                principalTable: "Traits",
                principalColumn: "traitId",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_UserTrait_Users_userId",
                table: "UserTrait",
                column: "userId",
                principalTable: "Users",
                principalColumn: "userId",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
