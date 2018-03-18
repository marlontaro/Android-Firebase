using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Api.WebSite.Controllers
{
    [Produces("application/json")]
    [Route("api/version")]
    public class VersionController : Controller
    {
        // GET: api/Version
        [HttpGet]
        public IActionResult Get()
        {
            string mensaje = "Version 1";
            return Ok(mensaje);
        }

     
    }
}
