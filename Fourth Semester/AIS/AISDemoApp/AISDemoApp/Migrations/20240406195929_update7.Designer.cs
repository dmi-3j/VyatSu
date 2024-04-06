﻿// <auto-generated />
using System;
using AISDemoApp;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace AISDemoApp.Migrations
{
    [DbContext(typeof(Context))]
    [Migration("20240406195929_update7")]
    partial class update7
    {
        /// <inheritdoc />
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "8.0.3")
                .HasAnnotation("Relational:MaxIdentifierLength", 63);

            NpgsqlModelBuilderExtensions.UseIdentityByDefaultColumns(modelBuilder);

            modelBuilder.Entity("AISDemoApp.Cart", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid");

                    b.Property<Guid>("UserId")
                        .HasColumnType("uuid");

                    b.HasKey("Id");

                    b.HasIndex("UserId")
                        .IsUnique();

                    b.ToTable("Cart");
                });

            modelBuilder.Entity("AISDemoApp.CartItem", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid");

                    b.Property<Guid>("CartId")
                        .HasColumnType("uuid");

                    b.Property<Guid>("InventoryId")
                        .HasColumnType("uuid");

                    b.HasKey("Id");

                    b.HasIndex("CartId");

                    b.HasIndex("InventoryId");

                    b.ToTable("CartItems");
                });

            modelBuilder.Entity("AISDemoApp.Inventory", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid");

                    b.Property<string>("InventoryName")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("InventoryType")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("PhotoPath")
                        .HasColumnType("text");

                    b.Property<decimal>("RentPrice")
                        .HasColumnType("numeric");

                    b.Property<decimal>("Size")
                        .HasColumnType("numeric");

                    b.HasKey("Id");

                    b.ToTable("Inventory");
                });

            modelBuilder.Entity("AISDemoApp.User", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid");

                    b.Property<DateTime>("DateOfBirth")
                        .HasColumnType("date");

                    b.Property<string>("FirstName")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("LastName")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("Password")
                        .HasColumnType("text");

                    b.Property<string>("PhoneNumber")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<string>("Username")
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.ToTable("Users");
                });

            modelBuilder.Entity("AISDemoApp.UserRole", b =>
                {
                    b.Property<Guid>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("uuid");

                    b.Property<string>("Role")
                        .IsRequired()
                        .HasColumnType("text");

                    b.Property<Guid>("UserId")
                        .HasColumnType("uuid");

                    b.HasKey("Id");

                    b.HasIndex("UserId");

                    b.ToTable("UserRoles");
                });

            modelBuilder.Entity("AISDemoApp.Cart", b =>
                {
                    b.HasOne("AISDemoApp.User", "User")
                        .WithOne("Cart")
                        .HasForeignKey("AISDemoApp.Cart", "UserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("User");
                });

            modelBuilder.Entity("AISDemoApp.CartItem", b =>
                {
                    b.HasOne("AISDemoApp.Cart", "Cart")
                        .WithMany("CartItems")
                        .HasForeignKey("CartId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("AISDemoApp.Inventory", "Inventory")
                        .WithMany("CartItems")
                        .HasForeignKey("InventoryId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Cart");

                    b.Navigation("Inventory");
                });

            modelBuilder.Entity("AISDemoApp.UserRole", b =>
                {
                    b.HasOne("AISDemoApp.User", null)
                        .WithMany("UserRoles")
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("AISDemoApp.Cart", b =>
                {
                    b.Navigation("CartItems");
                });

            modelBuilder.Entity("AISDemoApp.Inventory", b =>
                {
                    b.Navigation("CartItems");
                });

            modelBuilder.Entity("AISDemoApp.User", b =>
                {
                    b.Navigation("Cart")
                        .IsRequired();

                    b.Navigation("UserRoles");
                });
#pragma warning restore 612, 618
        }
    }
}
