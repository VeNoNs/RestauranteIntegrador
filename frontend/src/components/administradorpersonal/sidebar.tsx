'use client';
import React from 'react';
import { useRouter } from 'next/navigation'; // Cambiado a next/navigation

const Sidebar: React.FC = () => {
  const router = useRouter();

  // Usuario logueado (ejemplo estático, puedes reemplazarlo con datos dinámicos)
  const usuarioLogueado = 'Juan Pérez'; // Aquí puedes obtener el nombre real del usuario desde el contexto o API
  const rolUsuario = 'Administrador';  // Ejemplo de rol, puede ser dinámico

  const handleNavigation = (path: string) => {
    router.push(path); // Usa router.push de next/navigation
  };

  const handleLogout = () => {
    
    router.push('/');
  };

  return (
    <aside className="w-64 bg-gray-100 h-full p-6 flex flex-col justify-between">
      <div>
        <h2 className="text-2xl font-bold text-black mb-8">Dish Delight</h2>
        <nav>
          <ul>
          <li className="mb-6">
              <button
                onClick={() => handleNavigation('/administradorpersonal/local')}
                className="text-gray-600 hover:text-black"
              >
                🏠 Local
              </button>
            </li>
            <li className="mb-6">
              <button
                onClick={() => handleNavigation('/administradorpersonal/mesas')}
                className="text-gray-600 hover:text-black"
              >
                📋 Mesas
              </button>
            </li>
            <li className="mb-6">
              <button
                onClick={() => handleNavigation('/administradorpersonal/productos')}
                className="text-gray-600 hover:text-black"
              >
                🍽️ Productos
              </button>
            </li>
            <li className="mb-6">
              <button
                onClick={() => handleNavigation('/administradorpersonal/empleados')}
                className="text-gray-600 hover:text-black"
              >
                👥 Empleados
              </button>
            </li>
          </ul>
        </nav>
      </div>

      {/* Mostrar quién está logueado y su rol */}
      <div className="mt-8">
        <p className="text-black font-bold mb-2">👤 {usuarioLogueado}</p>
        <p className="text-gray-600 mb-4">{rolUsuario}</p>

        {/* Botón de logout */}
        <button
          onClick={handleLogout}
          className="w-full bg-red-500 text-white py-2 px-4 rounded hover:bg-red-600"
        >
          Cerrar Sesión
        </button>
      </div>
    </aside>
  );
};

export default Sidebar;
