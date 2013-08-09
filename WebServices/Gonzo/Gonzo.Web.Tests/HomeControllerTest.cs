using Gonzo.Web.Controllers;
using Gonzo.Web.Tests.Fakes;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Gonzo.Web.Tests
{
    [TestClass]
    public class HomeControllerTest
    {
        [TestMethod]
        public void HomeController_GetCategoryProducts_ShouldReturnProductsInCategory()
        {
            // Arrange
            var categoryId = 1;
            var repository = new FakeProductRepository();
            var target = new HomeController(repository);

            // Act
            var result = target.GetCategoryProducts(categoryId).Data;
            
            // Assert
            Assert.IsTrue(result != null);
        }
    }
}
