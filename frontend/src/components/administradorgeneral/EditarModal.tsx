import React from 'react';

interface EditarModalProps {
  closeModal: () => void;
  empresa: {
    id: number;
    nombre: string;
    empresa: string;
    telefono: string;
    correo: string;
  };
}

const EditarModal: React.FC<EditarModalProps> = ({ closeModal, empresa }) => {
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
        <h2 className="text-2xl font-bold mb-4 text-black">Editar Empresa</h2>
        <form>
          <input
            type="text"
            placeholder="Nombre de la empresa"
            className="border p-2 mb-4 w-full rounded text-black"
            defaultValue={empresa.nombre}
          />
          <input
            type="text"
            placeholder="Teléfono"
            className="border p-2 mb-4 w-full rounded text-black"
            defaultValue={empresa.telefono}
          />
          <input
            type="email"
            placeholder="Correo"
            className="border p-2 mb-4 w-full rounded text-black"
            defaultValue={empresa.correo}
          />
          <button className="bg-black text-white px-4 py-2 rounded-full w-full hover:bg-gray-800">
            Editar
          </button>
        </form>
      </div>
    </div>
  );
};

export default EditarModal;
