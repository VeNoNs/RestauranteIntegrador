'use client';

import React, { useState } from 'react';
import ModalAgregarProducto from '@/components/administradorpersonal/ModalAgregarProducto';
import ModalEliminarProducto from '@/components/administradorpersonal/ModalEliminarProducto';
import ModalEditarProducto from '@/components/administradorpersonal/ModalEditarProducto';

const ProductosPage: React.FC = () => {
    const [isAgregarModalOpen, setIsAgregarModalOpen] = useState(false);
    const [isEliminarModalOpen, setIsEliminarModalOpen] = useState(false);
    const [isEditarModalOpen, setIsEditarModalOpen] = useState(false);
    const [selectedProducto, setSelectedProducto] = useState<{ id: string; nombre: string; precio: number } | null>(null);
    
    // Estado para la categoría seleccionada
    const [categoriaSeleccionada, setCategoriaSeleccionada] = useState('Entradas');
  
    // Productos de ejemplo
    const productos = [
      { id: '1', nombre: 'Producto 1', precio: 50, categoria: 'Entradas', img: '/path-to-image-1' },
      { id: '2', nombre: 'Producto 2', precio: 60, categoria: 'Plato Principal', img: '/path-to-image-2' },
      { id: '3', nombre: 'Producto 3', precio: 70, categoria: 'Sopa', img: '/path-to-image-3' },
      { id: '4', nombre: 'Producto 4', precio: 80, categoria: 'Parrilla', img: '/path-to-image-4' },
      // Agregar más productos con categorías diferentes
    ];
  
    const handleAgregarClick = () => {
      setIsAgregarModalOpen(true);
    };
  
    const handleEliminarClick = () => {
      setIsEliminarModalOpen(true);
    };
  
    const handleEditarClick = (producto: { id: string; nombre: string; precio: number }) => {
      setSelectedProducto(producto);
      setIsEditarModalOpen(true);
    };
  
    const handleUpdateProducto = (productoEditado: { id: string; nombre: string; precio: number }) => {
      console.log('Producto editado:', productoEditado);
    };
  
    // Filtrar los productos por categoría seleccionada
    const productosFiltrados = productos.filter(
      (producto) => producto.categoria === categoriaSeleccionada
    );
  
    return (
      <div>
        <h1 className="text-3xl font-bold text-black mb-4">Productos</h1>
  
        {/* Navegación de categorías */}
        <div className="flex justify-center mb-6 space-x-4">
          {['Entradas', 'Plato Principal', 'Sopa', 'Parrilla', 'Bebidas', 'Postres'].map((categoria) => (
            <button
              key={categoria}
              onClick={() => setCategoriaSeleccionada(categoria)}
              className={`py-2 px-4 border-b-2 ${categoriaSeleccionada === categoria ? 'border-red-500 text-red-500' : 'border-transparent text-black hover:text-red-500'}`}
            >
              {categoria}
            </button>
          ))}
        </div>
  
        {/* Botones superiores */}
        <div className="flex justify-end space-x-4 mb-4">
          <button
            onClick={handleAgregarClick}
            className="bg-black text-white py-2 px-4 rounded"
          >
            Agregar
          </button>
  
          <button
            onClick={handleEliminarClick}
            className="bg-black text-white py-2 px-4 rounded"
          >
            Eliminar
          </button>
        </div>
  
        {/* Lista de productos */}
        <div className="grid grid-cols-4 gap-4">
          {productosFiltrados.length > 0 ? (
            productosFiltrados.map((producto) => (
              <div key={producto.id} className="border p-4 rounded">
                <img
                  src={producto.img}
                  alt={producto.nombre}
                  className="w-full h-40 object-cover rounded"
                />
                <h2 className="mt-2 text-center text-lg text-black">{producto.nombre}</h2>
                <p className="text-center text-black">S/. {producto.precio.toFixed(2)}</p>
                <button
                  onClick={() => handleEditarClick(producto)}
                  className="w-full mt-2 bg-red-100 text-red-500 py-2 px-4 rounded"
                >
                  ✏️ Edit dish
                </button>
              </div>
            ))
          ) : (
            <p className="text-center col-span-4">No hay productos en esta categoría.</p>
          )}
        </div>
  
        {/* Modal para Agregar Producto */}
        {isAgregarModalOpen && (
          <ModalAgregarProducto onClose={() => setIsAgregarModalOpen(false)} />
        )}
  
        {/* Modal para Eliminar Producto */}
        {isEliminarModalOpen && (
          <ModalEliminarProducto onClose={() => setIsEliminarModalOpen(false)} />
        )}
  
        {/* Modal para Editar Producto */}
        {isEditarModalOpen && selectedProducto && (
          <ModalEditarProducto
            producto={selectedProducto}
            onClose={() => setIsEditarModalOpen(false)}
            onUpdate={handleUpdateProducto}
          />
        )}
      </div>
    );
  };
  
  export default ProductosPage;