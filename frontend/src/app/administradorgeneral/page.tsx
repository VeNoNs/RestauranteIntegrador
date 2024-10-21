'use client';
import "@/styles/globals.css";
import React, { useEffect, useState } from 'react';
import EmpresasTable from '@/components/administradorgeneral/EmpresasTable';
import AgregarModal from '@/components/administradorgeneral/AgregarModal';
import EditarModal from '@/components/administradorgeneral/EditarModal';

interface Empresa {
    id: number;
    nombre: string;
    empresa: string;
    telefono: string;
    correo: string;
}

const AdminGeneralPage: React.FC = () => {
    const [empresas, setEmpresas] = useState<Empresa[]>([]);
    const [selectedEmpresa, setSelectedEmpresa] = useState<Empresa | null>(null);
    const [showAgregarModal, setShowAgregarModal] = useState(false);
    const [showEditarModal, setShowEditarModal] = useState(false);

    // Simulación de usuario logueado
    const loggedInUser = {
        nombre: 'Juan Pérez',
        rol: 'Administrador',
    };

    const handleAgregarClick = () => {
        setShowAgregarModal(true);
    };

    const handleEditarClick = (empresa: Empresa) => {
        setSelectedEmpresa(empresa);
        setShowEditarModal(true);
    };

    const closeModal = () => {
        setShowAgregarModal(false);
        setShowEditarModal(false);
    };


    const handleEmpresaAgregada = (nuevaEmpresa: Empresa) => {
        setEmpresas((prevEmpresas) => [...prevEmpresas, nuevaEmpresa]);
      };

      const handleEmpresaEditada = (empresaEditada: Empresa) => {
        setEmpresas((prevEmpresas) =>
          prevEmpresas.map((empresa) =>
            empresa.id === empresaEditada.id ? empresaEditada : empresa
          )
        );
      };
      

    // Fetch de empresas
    useEffect(() => {
        const fetchEmpresas = async () => {
            try {
                const response = await fetch('http://localhost:8080/administradorgeneral/api/verempresas');
                if (!response.ok) {
                    throw new Error('Error al obtener empresas');
                }
                const data = await response.json();
        
                // Mapeo para alinear las propiedades del backend con las del frontend
                const empresasMapped = data.map((empresa: any) => ({
                    id: empresa.idEmpresa,
                    nombre: empresa.nombreEmpresa,
                    telefono: empresa.telefono,
                    correo: empresa.usuario,  
                }));
                
                setEmpresas(empresasMapped);
            } catch (error) {
                console.error('Error fetching empresas:', error);
            }
        };

        fetchEmpresas();
    }, []);

    return (
        <div className="flex h-screen bg-gray-50">
            {/* Sidebar */}
            <aside className="w-64 bg-gray-100 p-6 flex flex-col justify-between">
                <div>
                    <h2 className="text-2xl font-bold text-black mb-8">Dish Delight</h2>
                    <nav>
                        <ul>
                            <li className="mb-6">
                                <a href="#" className="flex items-center text-gray-600 hover:text-black">
                                    <span className="mr-3">📋</span>
                                    <span className="font-semibold">Empresas</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>

                {/* Sección de Usuario Logueado */}
                <div className="border-t pt-4">
                    <p className="text-gray-700 font-medium">{loggedInUser.nombre}</p>
                    <p className="text-sm text-gray-500">{loggedInUser.rol}</p>
                    <button className="mt-4 bg-black text-white px-4 py-2 rounded-full hover:bg-gray-800 w-full">
                        Salir
                    </button>
                </div>
            </aside>

            {/* Main Content */}
            <main className="flex-1 p-8">
                <div className="flex justify-between items-center mb-6">
                    <h1 className="text-4xl font-bold text-black">Empresas</h1>
                    <div className="space-x-3">
                        <button
                            onClick={handleAgregarClick}
                            className="bg-black text-white rounded-full px-5 py-2 hover:bg-gray-800"
                        >
                            Agregar
                        </button>
                    </div>
                </div>
                <EmpresasTable empresas={empresas} onSelectEmpresa={handleEditarClick} />
                {showAgregarModal && <AgregarModal closeModal={closeModal} onEmpresaAgregada={handleEmpresaAgregada} />}
                {showEditarModal && selectedEmpresa && (
                    <EditarModal
                        closeModal={closeModal}
                        empresa={selectedEmpresa}
                        onEmpresaEditada={handleEmpresaEditada} // Pasamos la función de actualización aquí
                    />
                )}
            </main>
        </div>
    );
};

export default AdminGeneralPage;





/*'use client';
import "@/styles/globals.css";
import React, { useState } from 'react';
import EmpresasTable from '@/components/administradorgeneral/EmpresasTable';
import AgregarModal from '@/components/administradorgeneral/AgregarModal';
import EditarModal from '@/components/administradorgeneral/EditarModal';


interface Empresa {
    id: number;
    nombre: string;
    empresa: string;
    telefono: string;
    correo: string;
  }
  
  const AdminGeneralPage: React.FC = () => {
    const [selectedEmpresa, setSelectedEmpresa] = useState<Empresa | null>(null);
    const [showAgregarModal, setShowAgregarModal] = useState(false);
    const [showEditarModal, setShowEditarModal] = useState(false);
  
    // Simulación de usuario logueado
    const loggedInUser = {
      nombre: 'Juan Pérez',
      rol: 'Administrador',
    };
  
    const handleAgregarClick = () => {
      setShowAgregarModal(true);
    };
  
    const handleEditarClick = (empresa: Empresa) => {
      setSelectedEmpresa(empresa);
      setShowEditarModal(true);
    };
  
    const closeModal = () => {
      setShowAgregarModal(false);
      setShowEditarModal(false);
    };
  
    return (
      <div className="flex h-screen bg-gray-50">
        {/* Sidebar }
        <aside className="w-64 bg-gray-100 p-6 flex flex-col justify-between">
          <div>
            <h2 className="text-2xl font-bold text-black mb-8">Dish Delight</h2>
            <nav>
              <ul>
                <li className="mb-6">
                  <a href="#" className="flex items-center text-gray-600 hover:text-black">
                    <span className="mr-3">📋</span>
                    <span className="font-semibold">Empresas</span>
                  </a>
                </li>
              </ul>
            </nav>
          </div>
  
          {/* Sección de Usuario Logueado }
          <div className="border-t pt-4">
            <p className="text-gray-700 font-medium">{loggedInUser.nombre}</p>
            <p className="text-sm text-gray-500">{loggedInUser.rol}</p>
            <button className="mt-4 bg-black text-white px-4 py-2 rounded-full hover:bg-gray-800 w-full">
              Salir
            </button>
          </div>
        </aside>
  
        {/* Main Content }
        <main className="flex-1 p-8">
          <div className="flex justify-between items-center mb-6">
            <h1 className="text-4xl font-bold text-black">Empresas</h1>
            <div className="space-x-3">
              <button
                onClick={handleAgregarClick}
                className="bg-black text-white rounded-full px-5 py-2 hover:bg-gray-800"
              >
                Agregar
              </button>
            </div>
          </div>
          <EmpresasTable onSelectEmpresa={handleEditarClick} />
  
          {showAgregarModal && <AgregarModal closeModal={closeModal} />}
          {showEditarModal && selectedEmpresa && (
            <EditarModal closeModal={closeModal} empresa={selectedEmpresa} />
          )}
        </main>
      </div>
    );
  };
  
  export default AdminGeneralPage;*/