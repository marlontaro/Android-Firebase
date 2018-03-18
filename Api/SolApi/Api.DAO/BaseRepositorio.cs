using NPoco;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;

namespace Api.DAO
{
    public class BaseRepositorio
    {
        private string connectionString;

        public BaseRepositorio()
        {
            connectionString = @"Data Source=SQL5021.site4now.net;Initial Catalog=DB_A0969B_androiddemo;User Id=DB_A0969B_androiddemo_admin;Password=jiren2018;";
        }

        public IDatabase Connection
        {
            get
            {
                return new Database(connectionString, DatabaseType.SqlServer2012, SqlClientFactory.Instance);
            }
        }
    }
}
