import React from 'react';

interface RegisterModalProps {
  closeModal: () => void;
}

const RegisterModal: React.FC<RegisterModalProps> = ({ closeModal }) => {
  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="relative bg-white rounded-lg p-8 w-96"> {/* Ajuste de ancho */}
        {/* Botón de Cerrar en la esquina superior derecha */}
        <button
  onClick={closeModal}
  className="absolute top-2 right-2 bg-red-500 text-white rounded-md p-3 hover:bg-red-600"
>
          &times;
        </button>
        <h2 className="text-2xl font-bold mb-4 text-black">Registrarse</h2> {/* Letras en negro */}
        {/* Formulario de Registro */}
        <form>
          <input
            type="text"
            placeholder="Nombre completo"
            className="border p-2 mb-4 w-full rounded"
          />
          <input
            type="email"
            placeholder="Correo electrónico"
            className="border p-2 mb-4 w-full rounded"
          />
          <input
            type="password"
            placeholder="Contraseña"
            className="border p-2 mb-4 w-full rounded"
          />
          <button className="bg-black text-white px-4 py-2 rounded-full w-full hover:bg-gray-800">
            Registrarse
          </button>
        </form>
      </div>
    </div>
  );
};

export default RegisterModal;
