using System.Collections.Generic;
using Gonzo.Domain.Model;

namespace Gonzo.Domain.Contracts
{
    public interface ICategoriesRepository
    {
        Category FindByKey(int key);
        IEnumerable<Category> FindAll();
    }
}
