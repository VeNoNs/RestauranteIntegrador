'use client';
import React, { useState } from 'react';

interface ModalAgregarProductoProps {
  onClose: () => void;
}

const ModalAgregarProducto: React.FC<ModalAgregarProductoProps> = ({ onClose }) => {
  const [nombreProducto, setNombreProducto] = useState('');
  const [precioProducto, setPrecioProducto] = useState('');

  const handleAgregarProducto = () => {
    // Lógica para agregar el producto
    onClose();
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Agregar Producto</h2>

        <input
          type="text"
          placeholder="Nombre del Producto"
          value={nombreProducto}
          onChange={(e) => setNombreProducto(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        />
        <input
          type="number"
          placeholder="Precio del Producto"
          value={precioProducto}
          onChange={(e) => setPrecioProducto(e.target.value)}
          className="border w-full p-2 mb-4 text-black"
        />

        <div className="flex justify-end space-x-4">
          <button onClick={handleAgregarProducto} className="bg-black text-white py-2 px-4 rounded">
            Agregar
          </button>
          <button onClick={onClose} className="bg-gray-500 text-white py-2 px-4 rounded">
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
};

export default ModalAgregarProducto;
