'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Local {
  idLocal: number;
  ubicacion: string;
}

interface Producto {
  idComida: string;
  nombreComida: string;
  tipoComida: string;
  descripcion: string;
  precio: number;
  imagenUrl: string;
  local: {
    idLocal: number;
    ubicacion: string;
  };
}

interface ModalEditarProductoProps {
  producto: Producto;
  onClose: () => void;
  onUpdate: (productoEditado: Producto) => void;
}

const ModalEditarProducto: React.FC<ModalEditarProductoProps> = ({ producto, onClose, onUpdate }) => {
  const [nombreComida, setNombreComida] = useState(producto.nombreComida);
  const [tipoComida, setTipoComida] = useState(producto.tipoComida);
  const [descripcion, setDescripcion] = useState(producto.descripcion);
  const [precio, setPrecio] = useState(producto.precio.toString());
  const [imagenUrl, setImagenUrl] = useState(producto.imagenUrl);
  const [idLocal, setIdLocal] = useState(producto.local.idLocal);
  const [locales, setLocales] = useState<Local[]>([]);

  useEffect(() => {
    const fetchLocales = async () => {
      try {
        const response = await axios.get('http://localhost:8080/local/api/verlocales');
        setLocales(response.data);
      } catch (error) {
        console.error('Error al obtener los locales:', error);
      }
    };

    fetchLocales();
  }, []);

  const handleEditarProducto = async (e: React.FormEvent) => {
    e.preventDefault();

    const productoEditado = {
      idComida: producto.idComida,
      nombreComida,
      tipoComida,
      descripcion,
      precio: parseFloat(precio),
      imagenUrl,
      local: {
        idLocal,
      },
    };

    try {
      await axios.put(`http://localhost:8080/comida/api/editar/${producto.idComida}`, productoEditado);
      onUpdate({
        ...productoEditado,
        local: {
          idLocal,
          ubicacion: locales.find((local) => local.idLocal === idLocal)?.ubicacion || '',
        },
      });
      onClose();
    } catch (error) {
      console.error('Error al editar producto:', error);
    }
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="bg-white p-8 rounded shadow-lg relative">
        <button onClick={onClose} className="absolute top-2 right-2 text-red-500 text-xl">
          ✖
        </button>
        <h2 className="text-2xl mb-4 font-bold text-black">Editar Comida</h2>

        <form onSubmit={handleEditarProducto}>
          <input
            type="text"
            placeholder="Nombre de la Comida"
            value={nombreComida}
            onChange={(e) => setNombreComida(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          />
          
          <select
            value={tipoComida}
            onChange={(e) => setTipoComida(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          >
            <option value="">Seleccionar Tipo de Comida</option>
            <option value="Entradas">Entradas</option>
            <option value="Plato Principal">Plato Principal</option>
            <option value="Sopa">Sopa</option>
            <option value="Parrilla">Parrilla</option>
            <option value="Bebidas">Bebidas</option>
            <option value="Postres">Postres</option>
          </select>

          <textarea
            placeholder="Descripción"
            value={descripcion}
            onChange={(e) => setDescripcion(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
          />
          <input
            type="number"
            placeholder="Precio"
            value={precio}
            onChange={(e) => setPrecio(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
            required
          />
          <input
            type="text"
            placeholder="URL de la imagen"
            value={imagenUrl}
            onChange={(e) => setImagenUrl(e.target.value)}
            className="border w-full p-2 mb-4 text-black"
          />

          <select
            value={idLocal}
            onChange={(e) => setIdLocal(Number(e.target.value))}
            className="border w-full p-2 mb-4 text-black"
            required
          >
            <option value="">Seleccionar Local</option>
            {locales.map((local) => (
              <option key={local.idLocal} value={local.idLocal}>
                {local.ubicacion}
              </option>
            ))}
          </select>

          <div className="flex justify-end space-x-4">
            <button type="submit" className="bg-green-500 text-white py-2 px-4 rounded">
              Guardar
            </button>
            <button onClick={onClose} className="bg-red-500 text-white py-2 px-4 rounded">
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ModalEditarProducto;
