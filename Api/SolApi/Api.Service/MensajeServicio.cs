using Api.DAO;
using Api.Entidad;
using Api.Entidad.Api;
using System;
using System.Collections.Generic;
using System.Text;

namespace Api.Service
{
    public class MensajeServicio
    {
        private readonly MensajeRepositorio mensajeRepository;

        public MensajeServicio() {
            mensajeRepository = new MensajeRepositorio();
        }

        public Data<MensajeQueryOutput> GetAll(int id)
        {
            Data<MensajeQueryOutput> data = new Data<MensajeQueryOutput>();
            IList<MensajeQueryOutput> list = new List<MensajeQueryOutput>();

            list = mensajeRepository.GetAll(id);
            data.data = list;

            if (list.Count != 0)
            {
                data.message = "";
                data.status = Status.Ok;

            }
            else
            {
                data.message = "No hay mensajes";
                data.status = Status.Error;
            }


            return data;
        }


        public CheckStatus Add(MensajeInput input)
        {
            CheckStatus checkstatus = new CheckStatus();
            Mensaje mensaje = new Mensaje();
            mensaje.IdMensaje = 0;
            mensaje.De = input.de;
            mensaje.Para = input.para;
            mensaje.Contenido = input.contenido;

            checkstatus = Validate(input);
            if (checkstatus.status.Equals(Status.Ok))
            {
                checkstatus = mensajeRepository.Add(mensaje);
            }
            return checkstatus;
        }

        public CheckStatus Validate(MensajeInput input)
        {
            CheckStatus checkstatus = new CheckStatus();
            checkstatus.status = Status.Ok;

            string mensaje = "";
            
            if (input.de == 0)
            {
                mensaje += "Debe indentificarse";
            }

            if (input.para == 0)
            {
                mensaje += "Debe ingresar a quien va dirigido el mensaje";
            }

            if (string.IsNullOrWhiteSpace(input.contenido))
            {
                mensaje += "Debe ingresar mensaje.";
            }



            if (mensaje.Trim().Length != 0)
            {
                checkstatus.status = Status.Error;
                checkstatus.message = mensaje;

            }

            return checkstatus;
        }
    }
}
