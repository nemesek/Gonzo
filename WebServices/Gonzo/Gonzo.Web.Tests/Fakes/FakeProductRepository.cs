using System;
using System.Collections.Generic;
using Gonzo.Domain.Contracts;
using Gonzo.Domain.Model;

namespace Gonzo.Web.Tests.Fakes
{
    public class FakeProductRepository : IProductsRepository
    {
        private readonly List<Product> _products;

        public FakeProductRepository()
        {
            _products = new List<Product>
                            {
                                new Product {ProductId = 1, CategoryId = 1},
                                new Product {ProductId = 2, CategoryId = 1},
                                new Product {ProductId = 3, CategoryId = 2}
                            };
        }
        public void Add(ref Product item)
        {
            throw new NotImplementedException();
        }

        public void Delete(int key)
        {
            throw new NotImplementedException();
        }

        public Domain.Model.Product FindByKey(int key)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Product> FindAll()
        {
            //throw new NotImplementedException();
            return _products;
        }

        public int GetCount(string filterExpression)
        {
            throw new NotImplementedException();
        }

        public void SaveChanges()
        {
            throw new NotImplementedException();
        }
    }
}
