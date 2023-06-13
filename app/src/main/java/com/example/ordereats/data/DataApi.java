package com.example.ordereats.data;

public class DataApi {
    public String ip="http://192.168.100.37/enlaceApiConeccionDBordereats/";

    /*API platillos*/
    public String insertarPlatillo=ip+"PlatoApi.php?accion=InsertarPlatillo";
    public String obtenerPlatoPorId=ip+"PlatoApi.php?accion=ObtenerPlatoPorId";
    public String obtenerPlatillos=ip+"PlatoApi.php?accion=ObtenerPlatillos";
    public String eliminarPlatoPorId=ip+"PlatoApi.php?accion=EliminarPlatoPorId";
    public String actualizarPlato=ip+"PlatoApi.php?accion=ActualizarPlato";

    /*API Guarniciones */
    public String insertarGuarnicion=ip+"GuarnicionesApi.php?accion=InsertarGuarnicion";
    public String obtenerTodosGuarnicion=ip+"GuarnicionesApi.php?accion=ObtenerTodosGuarnicion";
    public String eliminarGuarnicionPorId=ip+"GuarnicionesApi.php?accion=EliminarGuarnicionPorId";
    public String obtenerGuarnicionPorId=ip+"GuarnicionesApi.php?accion=ObtenerGuarnicionPorId";
    public String actualizarGuarnicion=ip+"GuarnicionesApi.php?accion=ActualizarGuarnicion";

    /*API Ingredientes especiales*/
    public String insertarIngredienteEspecial=ip+"IngredienteEspecialApi.php?accion=InsertarIngredienteEspecial";
    public String obtenerTodosIngredientesEspeciales=ip+"IngredienteEspecialApi.php?accion=ObtenerTodosIngredientesEspeciales";
    public String eliminarIngredienteEspecial=ip+"IngredienteEspecialApi.php?accion=EliminarIngredienteEspecial";
    public String actualizarIngredienteEspecial=ip+"IngredienteEspecialApi.php?accion=ActualizarIngredienteEspecial";
    public String obtenerIngredienteEspecialPorId=ip+"IngredienteEspecialApi.php?accion=ObtenerIngredienteEspecialPorId";


}
