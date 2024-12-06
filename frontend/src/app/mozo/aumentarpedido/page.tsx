'use client';
import "@/styles/globals.css";
import React, { useState, useEffect } from 'react';
import ModalCarrito from '@/components/cliente/ModalCarrito';
import ModalSeleccionarEmpresaLocal from '@/components/cliente/ModalSeleccionarEmpresaLocal'; // Importar el modal de selección
import axios from 'axios'; // Asegúrate de importar axios
import { Producto } from "@/types/producto"; // Importa el tipo Producto correctamente

interface Empresa {
  idEmpresa: number;
  nombreEmpresa: string;
}

interface Local {
  idLocal: number;
  ubicacion: string;
}

// Extender Producto para manejar cantidad en el carrito
interface ProductoCarrito extends Producto {
  cantidad: number; // Añadir propiedad cantidad para el carrito
}

const ClientePage: React.FC = () => {
  const [categoriaSeleccionada, setCategoriaSeleccionada] = useState('Entradas');
  const [carrito, setCarrito] = useState<ProductoCarrito[]>([]); // Usar ProductoCarrito
  const [showModalCarrito, setShowModalCarrito] = useState(false);
  const [showModalSeleccion, setShowModalSeleccion] = useState(true); // Mostrar modal de selección inicialmente
  const [productos, setProductos] = useState<Producto[]>([]);
  const [empresaSeleccionada, setEmpresaSeleccionada] = useState<Empresa | null>(null);
  const [localSeleccionado, setLocalSeleccionado] = useState<Local | null>(null);

  // Agregar producto al carrito con cantidad
  const agregarAlCarrito = (producto: Producto) => {
    // Si el producto ya está en el carrito, solo aumenta su cantidad
    const productoEnCarrito = carrito.find((item) => item.idComida === producto.idComida);

    if (productoEnCarrito) {
      setCarrito(
        carrito.map((item) =>
          item.idComida === producto.idComida
            ? { ...item, cantidad: (item.cantidad || 1) + 1 } // Aumenta la cantidad
            : item
        )
      );
    } else {
      setCarrito([...carrito, { ...producto, cantidad: 1 }]); // Agrega producto con cantidad inicial 1
    }
  };

  const handleCategoriaClick = (categoria: string) => {
    setCategoriaSeleccionada(categoria);
  };

  const toggleModalCarrito = () => {
    setShowModalCarrito(!showModalCarrito);
  };

  // Callback para manejar la selección de empresa y local
  const handleEmpresaLocalSeleccionado = async (empresa: Empresa, local: Local) => {
    setEmpresaSeleccionada(empresa);
    setLocalSeleccionado(local);

    try {
      // Hacer una solicitud para obtener las comidas según la empresa y el local seleccionado
      const response = await axios.get('http://localhost:8080/comida/api/vercomidas');
      // Filtrar los productos (comidas) por el local seleccionado
      const comidasFiltradas = response.data.filter((comida: Producto) => comida.local.idLocal === local.idLocal);
      setProductos(comidasFiltradas);
    } catch (error) {
      console.error('Error al obtener las comidas:', error);
    }
  };

  // Filtrar productos por la categoría seleccionada (tipo de comida)
  const productosFiltrados = productos.filter((producto) => producto.tipoComida === categoriaSeleccionada);

  return (
    <div className="p-8 bg-gray-50 min-h-screen relative">
      {/* Header con nombre y carrito */}
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold text-black">Dish Delight</h1>
        <button onClick={toggleModalCarrito} className="text-black text-3xl">
          🛒
        </button>
      </div>

      <h2 className="text-4xl font-bold mb-8 text-black text-center">Elija su Menú :D</h2>

      {/* Mostrar el modal de selección de empresa y local */}
      {showModalSeleccion && (
        <ModalSeleccionarEmpresaLocal
          onEmpresaLocalSeleccionado={handleEmpresaLocalSeleccionado}
          onClose={() => setShowModalSeleccion(false)}
        />
      )}

      {/* Mostrar las categorías solo si se ha seleccionado una empresa y un local */}
      {empresaSeleccionada && localSeleccionado && (
        <>
          {/* Categorías en formato de pestañas */}
          <div className="flex justify-center space-x-4 mb-12">
            {['Entradas', 'Plato Principal', 'Parrilla', 'Bebidas', 'Postres'].map((categoria) => (
              <button
                key={categoria}
                onClick={() => handleCategoriaClick(categoria)}
                className={`py-2 px-4 text-lg rounded-lg transition-all ${
                  categoriaSeleccionada === categoria
                    ? 'bg-red-600 text-white'
                    : 'bg-gray-200 text-black hover:bg-gray-300'
                }`}
              >
                {categoria}
              </button>
            ))}
          </div>

          {/* Grid de productos */}
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            {productosFiltrados.length > 0 ? (
              productosFiltrados.map((producto) => (
                <div key={producto.idComida} className="border shadow-lg p-4 rounded-lg text-center bg-white hover:shadow-2xl text-black">
                  <img
                    src={producto.imagenUrl || 'assets/imagen5.jpeg'}
                    alt={producto.nombreComida}
                    className="w-full h-40 object-cover mb-4 rounded-lg"
                  />
                  <h2 className="text-xl font-bold text-black">{producto.nombreComida}</h2>
                  <p className="text-gray-500 text-lg">S/. {producto.precio.toFixed(2)}</p>
                  <button
                    onClick={() => agregarAlCarrito(producto)}
                    className="bg-red-500 text-white mt-4 py-2 px-6 rounded-lg hover:bg-red-600 transition-all"
                  >
                    Agregar al pedido
                  </button>
                </div>
              ))
            ) : (
              <p className="text-red-500">No hay productos disponibles en esta categoría.</p>
            )}
          </div>
        </>
      )}

      {/* Renderizando el Modal del Carrito */}
      {showModalCarrito && <ModalCarrito carrito={carrito} setCarrito={setCarrito} onClose={toggleModalCarrito} />}
    </div>
  );
};

export default ClientePage;
