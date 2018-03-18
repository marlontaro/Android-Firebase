using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Api.Entidad.Api;
using Api.Service;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Api.WebSite.Controllers
{
    [Produces("application/json")]
    [Route("api/usuario")]
    public class UsuarioController : Controller
    {
        private readonly UsuarioServicio usuarioRepository;

        public UsuarioController()
        {
            usuarioRepository = new UsuarioServicio();
        }

        // GET: api/Usuario
        [HttpGet]
        public IActionResult Get()
        {
            Data<UsuarioQueryOutput> data = new Data<UsuarioQueryOutput>();
            data = usuarioRepository.GetAll();

            if (data.status.Equals(Status.Error))
            {
                return NotFound(data);
            }
            return Ok(data);
        }

        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {

            string mensaje = "No existe implementacion";
            return Ok(mensaje);

        }


     

        // POST: api/Usuario
        [HttpPost]
        public IActionResult Post([FromBody]UsuarioInput input)
        {
            CheckStatus checkStatus = new CheckStatus();
            checkStatus = usuarioRepository.Login(input);

            return Ok(checkStatus);
        }
        
        // PUT: api/Usuario/5
        [HttpPut("{id}")]
        public IActionResult Put(int id, [FromBody]string value)
        {
            string mensaje = "No existe implementacion";
            return Ok(mensaje);
        }
        
        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            string mensaje = "No existe implementacion";
            return Ok(mensaje);
        }
    }
}
