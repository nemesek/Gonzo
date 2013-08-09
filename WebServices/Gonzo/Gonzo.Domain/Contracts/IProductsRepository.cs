using System.Collections.Generic;
using Gonzo.Domain.Model;

namespace Gonzo.Domain.Contracts
{
    public interface IProductsRepository
    {
        void Add(ref Product item);
        void Delete(int key);
        Product FindByKey(int key);
        IEnumerable<Product> FindAll();
        int GetCount(string filterExpression);
        void SaveChanges();
    }
}
