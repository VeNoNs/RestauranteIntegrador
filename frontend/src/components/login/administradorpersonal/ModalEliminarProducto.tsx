'use client';
import React, { useState } from 'react';

interface ModalEliminarProductoProps {
  onClose: () => void;
}

const ModalEliminarProducto: React.FC<ModalEliminarProductoProps> = ({ onClose }) => {
  const [searchTerm, setSearchTerm] = useState('');

  const handleEliminarProducto = (productoId: string) => {
    // Lógica para eliminar el producto
    onClose();
  };

  const productos = [
    { id: '1', nombre: 'Producto 1' },
    { id: '2', nombre: 'Producto 2' },
  ];

  const filteredProductos = productos.filter((producto) =>
    producto.nombre.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Eliminar Producto</h2>

        <input
          type="text"
          placeholder="Buscar producto"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        />

        <ul className="text-black">
          {filteredProductos.map((producto) => (
            <li key={producto.id} className="mb-2 flex justify-between items-center">
              <span>{producto.nombre}</span>
              <button
                onClick={() => handleEliminarProducto(producto.id)}
                className="bg-red-500 text-white py-1 px-2 rounded"
              >
                Eliminar
              </button>
            </li>
          ))}
        </ul>

        <div className="flex justify-end">
          <button onClick={onClose} className="bg-gray-500 text-white py-2 px-4 rounded">
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalEliminarProducto;
