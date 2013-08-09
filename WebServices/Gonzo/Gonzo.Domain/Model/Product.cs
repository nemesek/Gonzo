using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Gonzo.Domain.Model
{
    public class Product
    {
        public int ProductId { get; set; }
        public string DisplayName { get; set; }
        public string Description { get; set; }
        public int UnitsInStock { get; set; }
        public DateTime CreateDate { get; set; }
        public int CategoryId { get; set; }
    }
}
