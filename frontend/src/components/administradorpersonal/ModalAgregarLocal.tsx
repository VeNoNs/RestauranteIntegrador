import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Empresa {
  idEmpresa: number;
  nombreEmpresa: string;
}

interface ModalAgregarLocalProps {
  onClose: () => void;
}

const ModalAgregarLocal: React.FC<ModalAgregarLocalProps> = ({ onClose }) => {
  const [nroMesa, setNroMesa] = useState<number | null>(null);
  const [ubicacion, setUbicacion] = useState<string>('');
  const [idEmpresa, setIdEmpresa] = useState<number | null>(null);
  const [empresas, setEmpresas] = useState<Empresa[]>([]); // Estado para guardar las empresas

  // Obtener la lista de empresas desde la API
  useEffect(() => {
    const obtenerEmpresas = async () => {
      try {
        const response = await axios.get('http://localhost:8080/empresa/api/verempresas');
        setEmpresas(response.data);
      } catch (error) {
        console.error('Error al obtener empresas:', error);
      }
    };

    obtenerEmpresas();
  }, []);

  const handleAgregarLocal = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!nroMesa || !ubicacion || !idEmpresa) {
      console.error('Todos los campos son obligatorios');
      return;
    }

    const nuevoLocal = {
      nroMesa,
      ubicacion,
      empresa: { idEmpresa }, // Relacionamos el local con la empresa seleccionada
    };

    try {
      await axios.post('http://localhost:8080/local/api/nuevo', nuevoLocal);
      console.log('Local agregado con éxito');
      onClose();
    } catch (error) {
      console.error('Error al agregar el local:', error);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="relative bg-white rounded-lg p-8 w-96">
        <button
          onClick={onClose}
          className="absolute top-2 right-2 bg-red-500 text-white rounded-md p-2 hover:bg-red-600"
        >
          &times;
        </button>
        <h2 className="text-2xl font-bold mb-4 text-black">Agregar Local</h2>
        <form onSubmit={handleAgregarLocal}>
          <div className="mb-4">
            <label className="block text-gray-700">Número de Mesas:</label>
            <input
              type="number"
              value={nroMesa || ''}
              onChange={(e) => setNroMesa(parseInt(e.target.value))}
              className="border p-2 w-full rounded"
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700">Ubicación:</label>
            <input
              type="text"
              value={ubicacion}
              onChange={(e) => setUbicacion(e.target.value)}
              className="border p-2 w-full rounded"
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700">Empresa:</label>
            <select
              value={idEmpresa || ''}
              onChange={(e) => setIdEmpresa(parseInt(e.target.value))}
              className="border p-2 w-full rounded"
            >
              <option value="" disabled>Seleccione una empresa</option>
              {empresas.map((empresa) => (
                <option key={empresa.idEmpresa} value={empresa.idEmpresa}>
                  {empresa.nombreEmpresa}
                </option>
              ))}
            </select>
          </div>
          <button type="submit" className="bg-green-500 text-white px-4 py-2 rounded w-full hover:bg-green-600">
            Agregar Local
          </button>
        </form>
      </div>
    </div>
  );
};

export default ModalAgregarLocal;
