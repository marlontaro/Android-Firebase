using Api.DAO;
using Api.Entidad;
using Api.Entidad.Api;
using System;
using System.Collections.Generic;

namespace Api.Service
{
    public class UsuarioServicio
    {
        private readonly UsuarioRepositorio mensajeRepository;

        public UsuarioServicio()
        {
            mensajeRepository = new UsuarioRepositorio();
        }


        public Data<UsuarioQueryOutput> GetAll()
        {
            Data<UsuarioQueryOutput> data = new Data<UsuarioQueryOutput>();
            IList<UsuarioQueryOutput> list = new List<UsuarioQueryOutput>();

            list = mensajeRepository.GetAll();
            data.data = list;

            if (list.Count != 0)
            {
                data.message = "";
                data.status = Status.Ok;

            }
            else
            {
                data.message = "No hay usuarios";
                data.status = Status.Error;
            }


            return data;

        }


        public CheckStatus Login(UsuarioInput input)
        {
            CheckStatus checkStatus = new CheckStatus();
            
            Usuario usuario = new Usuario();
            usuario.IdUsuario = 0;
            usuario.Nombre = input.nombre;
            usuario.Correo = input.correo;
            usuario.Tipo = input.tipo;
            usuario.IdSocial = input.socialId;
            usuario.IdFirebase = input.firebase;

            checkStatus = mensajeRepository.Login(usuario);

            return checkStatus;
        }


    }
}
