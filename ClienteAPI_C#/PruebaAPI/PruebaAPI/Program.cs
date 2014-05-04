using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace MyWebRequest
{


    class Program
    {
        static void Main(string[] args)
        {
            //create the constructor with post type and few data
            MyWebClient myRequest = new MyWebClient();
            //show the response string on the console screen.
            Console.WriteLine(myRequest.takeResources());
        }
    }
}
