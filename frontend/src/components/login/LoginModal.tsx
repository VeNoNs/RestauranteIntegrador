import React from 'react';

interface LoginModalProps {
  closeModal: () => void;
}

const LoginModal: React.FC<LoginModalProps> = ({ closeModal }) => {
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
        <h2 className="text-2xl font-bold mb-4 text-black">Iniciar Sesión</h2> {/* Letras en negro */}
        {/* Formulario de Iniciar Sesión */}
        <form>
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
          <button className="bg-orange-500 text-white px-4 py-2 rounded-full w-full hover:bg-orange-600">
            Entrar
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginModal;
