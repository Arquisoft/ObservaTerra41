using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace MyWebRequest
{
    public class MyWebClient
    {

        public String takeResources()
        {
            System.Net.WebClient wc = new System.Net.WebClient();
            string webData = wc.DownloadString("http://localhost:9000/api/country");


            return webData;
        }

    }


}
