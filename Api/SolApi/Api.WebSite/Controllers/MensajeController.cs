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
    [Route("api/mensaje")]
    public class MensajeController : Controller
    {
        private readonly MensajeServicio mensajeRepository;
        
        public MensajeController()
        {
            mensajeRepository = new MensajeServicio();

        }


        // GET: api/Mensaje
        [HttpGet]
        public IActionResult Get()
        {
            string mensaje = "No existe implementacion";
            return Ok(mensaje);
        }    

        // GET: api/Mensaje/5
        [HttpGet("{id}")]
        public IActionResult Get(int id)
          {
              Data<MensajeQueryOutput> data = new Data<MensajeQueryOutput>();
              data = mensajeRepository.GetAll(id);

              if (data.status.Equals(Status.Error))
              {
                  return NotFound(data);
              }
              return Ok(data);
          }
          
        // POST: api/Mensaje
        [HttpPost]
        public IActionResult Post([FromBody]MensajeInput input)
        {
            CheckStatus checkStatus = new CheckStatus();
            checkStatus = mensajeRepository.Add(input);

            return Ok(checkStatus);
        }
        
        // PUT: api/Mensaje/5
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
