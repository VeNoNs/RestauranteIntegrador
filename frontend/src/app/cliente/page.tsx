'use client';
import "@/styles/globals.css";
import React, { useState } from 'react';
import ModalCarrito from '@/components/cliente/ModalCarrito'; // Importando el modal como componente

interface Producto {
  id: number;
  nombre: string;
  precio: number;
}

const productos: { [key: string]: Producto[] } = {
  Entradas: [
    { id: 1, nombre: 'Ceviche', precio: 50.00 },
    { id: 2, nombre: 'Anticuchos', precio: 40.00 },
  ],
  'Plato principal': [
    { id: 3, nombre: 'Lomo Saltado', precio: 55.00 },
    { id: 4, nombre: 'Aji de Gallina', precio: 45.00 },
  ],
  Parrilla: [
    { id: 5, nombre: 'Parrilla Mixta', precio: 80.00 },
    { id: 6, nombre: 'Pollo a la Brasa', precio: 60.00 },
  ],
  Bebidas: [
    { id: 7, nombre: 'Pisco Sour', precio: 25.00 },
    { id: 8, nombre: 'Cerveza', precio: 20.00 },
  ],
  Postres: [
    { id: 9, nombre: 'Helado', precio: 15.00 },
    { id: 10, nombre: 'Tres Leches', precio: 18.00 },
  ],
};

const ClientePage: React.FC = () => {
  const [categoriaSeleccionada, setCategoriaSeleccionada] = useState('Entradas');
  const [carrito, setCarrito] = useState<Producto[]>([]);
  const [showModal, setShowModal] = useState(false); // Modal state

  const agregarAlCarrito = (producto: Producto) => {
    setCarrito([...carrito, producto]);
  };

  const handleCategoriaClick = (categoria: string) => {
    setCategoriaSeleccionada(categoria);
  };

  const toggleModal = () => {
    setShowModal(!showModal); // Toggle modal open/close
  };

  return (
    <div className="p-8 bg-gray-50 min-h-screen relative">
      {/* Header con nombre y carrito */}
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold text-black">Dish Delight</h1>
        <button onClick={toggleModal} className="text-black text-3xl">
          🛒
        </button>
      </div>

      <h2 className="text-4xl font-bold mb-8 text-black text-center">Elija su Menú :D</h2>

      {/* Categorías en formato de pestañas */}
      <div className="flex justify-center space-x-4 mb-12">
        {Object.keys(productos).map((categoria) => (
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
        {productos[categoriaSeleccionada] ? (
          productos[categoriaSeleccionada].map((producto) => (
            <div key={producto.id} className="border shadow-lg p-4 rounded-lg text-center bg-white hover:shadow-2xl">
              <img
                src="/default-product.jpg"
                alt={producto.nombre}
                className="w-full h-40 object-cover mb-4 rounded-lg"
              />
              <h2 className="text-xl font-bold text-black">{producto.nombre}</h2>
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

      {/* Renderizando el Modal del Carrito */}
      {showModal && (
        <ModalCarrito carrito={carrito} onClose={toggleModal} />
      )}
    </div>
  );
};

export default ClientePage;
