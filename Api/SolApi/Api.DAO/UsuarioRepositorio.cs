using Api.Entidad;
using Api.Entidad.Api;
using NPoco;
using System;
using System.Collections.Generic;

namespace Api.DAO
{
    public class UsuarioRepositorio
    {
        BaseRepositorio baseRepositorio;

        public UsuarioRepositorio() {
            baseRepositorio = new BaseRepositorio();
        }

        public CheckStatus Login(Usuario usuario) {
            CheckStatus checkStatus = new CheckStatus();
            UsuarioOutput usuarioSingle = null;
            //buscar
            using (IDatabase db = baseRepositorio.Connection)
            {

                usuarioSingle =
                    db.SingleOrDefault<UsuarioOutput>(@"select IdUsuario as id, Nombre as nombre
                        from [dbo].[Usuario]
                        where Tipo=@0 and IdSocial =@1 ", usuario.Tipo,usuario.IdSocial);

                if (usuarioSingle != null)
                {
                    checkStatus.id = usuarioSingle.id.ToString();
                    checkStatus.status = Status.Ok;
                }
                else {
                    //guardar
                    db.Insert<Usuario>(usuario);

                    checkStatus.status = Status.Ok;
                    checkStatus.id = usuario.IdUsuario.ToString();
                    checkStatus.message = "Se guardo usuario satisfactoriamente.";

                }
            }



            return checkStatus;
        }
        
        public IList<UsuarioQueryOutput> GetAll()
        {
            using (IDatabase db = baseRepositorio.Connection)
            {
                return db.Fetch<UsuarioQueryOutput>("select IdUsuario as id, Nombre as nombre, IdFirebase as firebase from [dbo].[Usuario]");
            }
        }

    }
}
