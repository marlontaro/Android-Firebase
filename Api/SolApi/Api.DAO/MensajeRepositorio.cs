using Api.Entidad;
using Api.Entidad.Api;
using NPoco;
using System;
using System.Collections.Generic;
using System.Text;

namespace Api.DAO
{
    public class MensajeRepositorio
    {
        BaseRepositorio baseRepositorio;

        public MensajeRepositorio()
        {
            baseRepositorio = new BaseRepositorio();
        }

        public CheckStatus Add(Mensaje prod)
        {
            CheckStatus checkstatus = new CheckStatus();
            using (IDatabase db = baseRepositorio.Connection)
            {

                db.Insert<Mensaje>(prod);

                checkstatus.status = Status.Ok;
                checkstatus.id = prod.IdMensaje.ToString();
                checkstatus.message = "Se guardo mensaje satisfactoriamente.";
            }

            return checkstatus;
        }

        public IList<MensajeQueryOutput> GetAll(int id)
        {
            using (IDatabase db = baseRepositorio.Connection)
            {
                string sql = String.Format(@"
                        select

                            men.IdMensaje as id,
                            usu.[Nombre] as de,
                            men.Contenido as mensaje
                        from Mensaje men
                        inner
                        join Usuario usu
                        on men.[De] = usu.[IdUsuario]
                        where [Para] = {0}",id);

                return db.Fetch<MensajeQueryOutput>(sql);
            }
        }


    }
}
