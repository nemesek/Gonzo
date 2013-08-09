using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Gonzo.Domain.Contracts;

namespace Gonzo.Web.Controllers
{
    public class HomeController : Controller
    {
        private readonly IProductsRepository _repository;
        //public HomeController():this(new ProductsRepository())
        //{
            
        //}

        public HomeController(IProductsRepository repository)
        {
            _repository = repository;
        }

        public ActionResult Index()
        {
            ViewBag.Message = "Modify this template to jump-start your ASP.NET MVC application.";

            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your app description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }

        public JsonResult GetCategoryProducts(int categoryId)
        {
            var products = _repository.FindAll().Where(p => p.CategoryId == categoryId);
            return Json(products, JsonRequestBehavior.AllowGet);
        }
    }
}
