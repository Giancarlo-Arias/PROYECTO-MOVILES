package com.example.ordereats.data;

public class DataApi {
    public static String ip="http://192.168.100.37/enlaceApiConeccionDBordereats/";

    /*API platillos*/
    public static String insertarPlatillo=ip+"PlatoApi.php?accion=InsertarPlatillo";
    public static String obtenerPlatoPorId=ip+"PlatoApi.php?accion=ObtenerPlatoPorId";
    public static String obtenerPlatillos=ip+"PlatoApi.php?accion=ObtenerPlatillos";
    public static String eliminarPlatoPorId=ip+"PlatoApi.php?accion=EliminarPlatoPorId";
    public static String actualizarPlato=ip+"PlatoApi.php?accion=ActualizarPlato";

    /*API Guarniciones */
    public static String insertarGuarnicion=ip+"GuarnicionesApi.php?accion=InsertarGuarnicion";
    public static String obtenerTodosGuarnicion=ip+"GuarnicionesApi.php?accion=ObtenerTodosGuarnicion";
    public static String eliminarGuarnicionPorId=ip+"GuarnicionesApi.php?accion=EliminarGuarnicionPorId";
    public static String obtenerGuarnicionPorId=ip+"GuarnicionesApi.php?accion=ObtenerGuarnicionPorId";
    public static String actualizarGuarnicion=ip+"GuarnicionesApi.php?accion=ActualizarGuarnicion";

    /*API Ingredientes especiales*/
    public static String insertarIngredienteEspecial=ip+"IngredienteEspecialApi.php?accion=InsertarIngredienteEspecial";
    public static String obtenerTodosIngredientesEspeciales=ip+"IngredienteEspecialApi.php?accion=ObtenerTodosIngredientesEspeciales";
    public static String eliminarIngredienteEspecial=ip+"IngredienteEspecialApi.php?accion=EliminarIngredienteEspecial";
    public static String actualizarIngredienteEspecial=ip+"IngredienteEspecialApi.php?accion=ActualizarIngredienteEspecial";
    public static String obtenerIngredienteEspecialPorId=ip+"IngredienteEspecialApi.php?accion=ObtenerIngredienteEspecialPorId";


}
