'use client';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Empresa {
  idEmpresa: number;
  nombreEmpresa: string;
}

interface Local {
  idLocal: number;
  ubicacion: string;
}

interface ModalSeleccionarEmpresaLocalProps {
  onEmpresaLocalSeleccionado: (empresa: Empresa, local: Local) => void;
  onClose: () => void;
}

const ModalSeleccionarEmpresaLocal: React.FC<ModalSeleccionarEmpresaLocalProps> = ({ onEmpresaLocalSeleccionado, onClose }) => {
  const [empresas, setEmpresas] = useState<Empresa[]>([]);
  const [locales, setLocales] = useState<Local[]>([]);
  const [empresaSeleccionada, setEmpresaSeleccionada] = useState<number | null>(null);
  const [localSeleccionado, setLocalSeleccionado] = useState<number | null>(null);

  // Fetch de empresas al cargar el modal
  useEffect(() => {
    const fetchEmpresas = async () => {
      try {
        const response = await axios.get('http://localhost:8080/empresa/api/verempresas');
        setEmpresas(response.data);
      } catch (error) {
        console.error('Error al obtener las empresas:', error);
      }
    };

    fetchEmpresas();
  }, []);

  // Fetch de locales según la empresa seleccionada
  useEffect(() => {
    if (empresaSeleccionada) {
      const fetchLocales = async () => {
        try {
          const response = await axios.get('http://localhost:8080/local/api/verlocales');
          
          console.log("Locales obtenidos:", response.data); // Verifica que el dato recibido es correcto
  
          // Accedemos correctamente a la relación empresa en cada local
          const localesFiltrados = response.data.filter((local: any) => local.empresa?.idEmpresa === empresaSeleccionada);
          console.log("Locales filtrados:", localesFiltrados);
          setLocales(localesFiltrados);
        } catch (error) {
          console.error('Error al obtener los locales:', error);
        }
      };
  
      fetchLocales();
    }
  }, [empresaSeleccionada]);

  const handleConfirmarSeleccion = () => {
    if (empresaSeleccionada && localSeleccionado) {
      const empresa = empresas.find(e => e.idEmpresa === empresaSeleccionada);
      const local = locales.find(l => l.idLocal === localSeleccionado);
      if (empresa && local) {
        onEmpresaLocalSeleccionado(empresa, local);
      }
    }
    onClose(); // Cierra el modal
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="bg-white p-6 rounded-lg">
        <h2 className="text-2xl font-bold mb-4">Seleccionar Empresa y Local</h2>
        {/* Selección de Empresa */}
        <select
          value={empresaSeleccionada ?? ''}
          onChange={(e) => setEmpresaSeleccionada(Number(e.target.value))}
          className="border p-2 mb-4 w-full"
        >
          <option value="">Seleccionar Empresa</option>
          {empresas.map((empresa) => (
            <option key={empresa.idEmpresa} value={empresa.idEmpresa}>
              {empresa.nombreEmpresa}
            </option>
          ))}
        </select>

        {/* Selección de Local */}
        <select
          value={localSeleccionado ?? ''}
          onChange={(e) => setLocalSeleccionado(Number(e.target.value))}
          className="border p-2 mb-4 w-full"
          disabled={!empresaSeleccionada} // Deshabilitado hasta que se seleccione la empresa
        >
          <option value="">Seleccionar Local</option>
          {locales.map((local) => (
            <option key={local.idLocal} value={local.idLocal}>
              {local.ubicacion}
            </option>
          ))}
        </select>

        <button onClick={handleConfirmarSeleccion} className="bg-green-500 text-white py-2 px-4 rounded">
          Confirmar
        </button>
        <button onClick={onClose} className="bg-red-500 text-white py-2 px-4 rounded ml-4">
          Cancelar
        </button>
      </div>
    </div>
  );
};

export default ModalSeleccionarEmpresaLocal;
