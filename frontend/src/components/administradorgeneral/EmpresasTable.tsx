import React, { useEffect, useState } from 'react';
import axios from 'axios';

interface Empresa {
  id: number;
  nombre: string;
  telefono: string;
  correo: string;
  administrador?: {
    nombreAdmin: string;
    correoAdmin: string;
  };
}

const EmpresasTable: React.FC<{ onSelectEmpresa: (empresa: Empresa) => void }> = ({ onSelectEmpresa }) => {
  const empresas: Empresa[] = [
    { id: 67, nombre: 'Owner', empresa: 'Your Friend Buzz', telefono: 'Australia', correo: 'email1@example.com' },
    { id: 76, nombre: 'Admin', empresa: 'Story Inc', telefono: 'Hong Kong', correo: 'email2@example.com' },
    { id: 87, nombre: 'User', empresa: 'Mixpanel', telefono: 'United States of America', correo: 'email3@example.com' },
    { id: 92, nombre: 'Visitor', empresa: 'Shopify', telefono: 'Canada', correo: 'email4@example.com' },
  ];

  return (
    <div className="bg-white shadow rounded-lg overflow-hidden">
      <table className="min-w-full">
        <thead className="bg-gray-50">
          <tr>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              ID Empresa
            </th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Nombre Empresa
            </th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Teléfono
            </th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Correo
            </th>
            <th className="py-3 px-4 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Acciones
            </th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {empresas.map((empresa) => (
            <tr key={empresa.id} className="hover:bg-purple-100">
              <td className="py-3 px-4 text-sm font-medium text-gray-900">{empresa.id}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.nombre}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.empresa}</td>
              <td className="py-3 px-4 text-sm text-gray-900">{empresa.telefono}</td>
              <td className="py-3 px-4">
                {/* Botón Editar dentro de la tabla con el nuevo estilo */}
                <button
                  className="bg-black text-white px-4 py-2 rounded-full hover:bg-gray-800"
                  onClick={() => onSelectEmpresa(empresa)}
                >
                  Editar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EmpresasTable;
