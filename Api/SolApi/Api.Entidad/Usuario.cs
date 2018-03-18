using NPoco;
using System;

namespace Api.Entidad
{
    [TableName("Usuario")]
    [PrimaryKey("IdUsuario")]
    public class Usuario
    {
        public int IdUsuario { get; set; }
        public string Nombre { get; set; }
        public string Correo { get; set; }
        public int Tipo { get; set; }
        public string IdSocial { get; set; }
        public string IdFirebase { get; set; }
    }
}
