'use client';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ModalAgregarLocal from '@/components/administradorpersonal/ModalAgregarLocal';
import ModalEliminarLocal from '@/components/administradorpersonal/ModalEliminarLocal';

interface Local {
  idLocal: number;
  nroMesa: number;
  ubicacion: string;
  empresa: {
    nombreEmpresa: string; // Si quieres mostrar el nombre de la empresa asociada
  };
}

const LocalesPage: React.FC = () => {
  const [isAgregarModalOpen, setIsAgregarModalOpen] = useState(false);
  const [isEliminarModalOpen, setIsEliminarModalOpen] = useState(false);
  const [localSeleccionado, setLocalSeleccionado] = useState<number | null>(null);
  const [locales, setLocales] = useState<Local[]>([]); // Estado para los locales

  // Función para obtener los locales desde la API
  useEffect(() => {
    const obtenerLocales = async () => {
      try {
        const response = await axios.get('http://localhost:8080/local/api/verlocales');
        setLocales(response.data);
      } catch (error) {
        console.error('Error al obtener los locales:', error);
      }
    };

    obtenerLocales();
  }, []);

  const handleAgregarClick = () => {
    setIsAgregarModalOpen(true);
  };

  const handleEliminarClick = () => {
    setIsEliminarModalOpen(true);
  };

  const seleccionarLocal = (id: number) => {
    setLocalSeleccionado(id);
  };

  return (
    <div>
      <h1 className="text-3xl font-bold text-black mb-4">Administrar Locales</h1>

      {/* Botones superiores */}
      <div className="flex justify-end space-x-4 mb-4">
        <button
          onClick={handleAgregarClick}
          className="bg-green-500 text-white py-2 px-4 rounded-md shadow-md hover:bg-green-700"
        >
          Agregar
        </button>

        <button
          onClick={handleEliminarClick}
          className="bg-red-500 text-white py-2 px-4 rounded-md shadow-md hover:bg-red-700"
        >
          Eliminar
        </button>
      </div>

      {/* Tabla de locales con estilo ajustado */}
      <div className="overflow-x-auto">
        <table className="min-w-full table-auto bg-white shadow-lg rounded-lg overflow-hidden">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 text-left text-black">ID Local</th>
              <th className="px-4 py-2 text-left text-black">Número de Mesas</th>
              <th className="px-4 py-2 text-left text-black">Ubicación</th>
              <th className="px-4 py-2 text-left text-black">Empresa</th>
            </tr>
          </thead>
          <tbody>
            {locales.map((local) => (
              <tr
                key={local.idLocal}
                className={`hover:bg-gray-200 cursor-pointer transition-colors ${
                  localSeleccionado === local.idLocal ? 'bg-purple-100' : ''
                }`}
                onClick={() => seleccionarLocal(local.idLocal)}
              >
                <td className="px-4 py-2 text-black border-b border-gray-200">{local.idLocal}</td>
                <td className="px-4 py-2 text-black border-b border-gray-200">{local.nroMesa}</td>
                <td className="px-4 py-2 text-black border-b border-gray-200">{local.ubicacion}</td>
                <td className="px-4 py-2 text-black border-b border-gray-200">{local.empresa.nombreEmpresa}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal para Agregar Local */}
      {isAgregarModalOpen && (
        <ModalAgregarLocal onClose={() => setIsAgregarModalOpen(false)} />
      )}

      {/* Modal para Eliminar Local */}
      {isEliminarModalOpen && (
        <ModalEliminarLocal onClose={() => setIsEliminarModalOpen(false)} />
      )}
    </div>
  );
};

export default LocalesPage;
