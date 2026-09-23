// URL base da tua API Spring Boot
const API_URL = 'http://localhost:8080';

// Função executada automaticamente assim que a página é aberta
document.addEventListener('DOMContentLoaded', () => {
    loadTeams();
});

// --- 1. Requisição GET: Buscar e listar equipas ---
async function loadTeams() {
    try {
        const response = await fetch(`${API_URL}/teams`);
        
        if (!response.ok) {
            throw new Error('Erro ao carregar as equipas do servidor.');
        }

        const teams = await response.json();
        const teamList = document.getElementById('teamList');
        
        // Limpa a lista antes de preencher
        teamList.innerHTML = '';

        if (teams.length === 0) {
            teamList.innerHTML = '<li class="py-2 text-slate-500">Nenhuma equipa registada ainda.</li>';
            return;
        }

        // Cria um elemento <li> para cada equipa vinda da API
        teams.forEach(team => {
            const li = document.createElement('li');
            li.className = 'py-3 flex justify-between items-center text-slate-300';
            li.innerHTML = `
                <span class="font-medium">${team.name}</span>
                <span class="text-xs bg-slate-800 text-slate-400 px-2 py-1 rounded">ID: ${team.id}</span>
            `;
            teamList.appendChild(li);
        });

    } catch (error) {
        console.error('Erro:', error);
        alert('Não foi possível ligar à API. Verifica se o Spring Boot está a correr.');
    }
}

// --- 2. Requisição POST: Enviar nova equipa para a API ---
async function createTeam() {
    const inputName = document.getElementById('teamName');
    const nameValue = inputName.value.trim();

    if (!nameValue) {
        alert('Por favor, escreve o nome da equipa!');
        return;
    }

    const newTeam = { name: nameValue };

    try {
        const response = await fetch(`${API_URL}/teams`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newTeam) // Transforma o objeto JavaScript num texto JSON
        });

        if (response.ok) {
            inputName.value = ''; // Limpa o campo de texto
            loadTeams(); // Recarrega a lista automaticamente na tela
            alert('Equipa criada com sucesso!');
        } else {
            alert('Erro ao criar a equipa no servidor.');
        }

    } catch (error) {
        console.error('Erro na requisição POST:', error);
    }
}