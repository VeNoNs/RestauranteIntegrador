import React from 'react';

interface AgregarModalProps {
  closeModal: () => void;
}

const AgregarModal: React.FC<AgregarModalProps> = ({ closeModal }) => {
  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="relative bg-white rounded-lg p-8 w-96">
        {/* Botón de Cerrar en la esquina superior derecha */}
        <button
          onClick={closeModal}
          className="absolute top-2 right-2 bg-red-500 text-white rounded-md px-2 hover:bg-red-600"
        >
          &times;
        </button>
        <h2 className="text-2xl font-bold mb-4">Agregar Empresa</h2>
        <form>
          <input
            type="text"
            placeholder="Nombre de la empresa"
            className="border p-2 mb-4 w-full rounded"
          />
          <input
            type="text"
            placeholder="Teléfono"
            className="border p-2 mb-4 w-full rounded"
          />
          <input
            type="email"
            placeholder="Correo"
            className="border p-2 mb-4 w-full rounded"
          />
          <button className="bg-black text-white px-4 py-2 rounded-full w-full hover:bg-gray-800">
            Agregar
          </button>
        </form>
      </div>
    </div>
  );
};

export default AgregarModal;
