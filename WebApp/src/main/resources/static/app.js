const state = {
    items: []
};

const inventoryTableBody = document.getElementById('inventoryTableBody');
const itemCount = document.getElementById('itemCount');
const avgQuality = document.getElementById('avgQuality');
const statusBadge = document.getElementById('statusBadge');
const itemForm = document.getElementById('itemForm');
const loadDemoBtn = document.getElementById('loadDemoBtn');
const updateBtn = document.getElementById('updateBtn');

function toNumber(value) {
    return Number.isFinite(value) ? value : 0;
}

function renderTable() {
    inventoryTableBody.innerHTML = '';

    state.items.forEach((item, index) => {
        const row = document.createElement('tr');

        const nameCell = document.createElement('td');
        nameCell.textContent = item.name;

        const sellInCell = document.createElement('td');
        const sellInInput = document.createElement('input');
        sellInInput.type = 'number';
        sellInInput.min = '0';
        sellInInput.value = item.sellIn;
        sellInInput.addEventListener('input', (event) => {
            item.sellIn = toNumber(Number(event.target.value));
        });
        sellInCell.appendChild(sellInInput);

        const qualityCell = document.createElement('td');
        const qualityInput = document.createElement('input');
        qualityInput.type = 'number';
        qualityInput.min = '0';
        qualityInput.value = item.quality;
        qualityInput.addEventListener('input', (event) => {
            item.quality = toNumber(Number(event.target.value));
        });
        qualityCell.appendChild(qualityInput);

        const actionCell = document.createElement('td');
        const deleteBtn = document.createElement('button');
        deleteBtn.textContent = 'Delete';
        deleteBtn.className = 'delete-btn';
        deleteBtn.addEventListener('click', () => {
            state.items.splice(index, 1);
            renderTable();
            updateSummary();
        });
        actionCell.appendChild(deleteBtn);

        row.appendChild(nameCell);
        row.appendChild(sellInCell);
        row.appendChild(qualityCell);
        row.appendChild(actionCell);
        inventoryTableBody.appendChild(row);
    });

    updateSummary();
}

function updateSummary() {
    itemCount.textContent = String(state.items.length);

    const totalQuality = state.items.reduce((sum, item) => sum + item.quality, 0);
    const avg = state.items.length ? (totalQuality / state.items.length).toFixed(1) : '0';
    avgQuality.textContent = avg;
    statusBadge.textContent = state.items.length ? 'Live' : 'Ready';
}

async function fetchJson(url, options = {}) {
    const response = await fetch(url, {
        headers: { 'Content-Type': 'application/json' },
        ...options
    });

    if (!response.ok) {
        throw new Error(`Request failed with status ${response.status}`);
    }

    return response.json();
}

async function loadDemoInventory() {
    try {
        statusBadge.textContent = 'Loading...';
        const demoItems = await fetchJson('/api/items/demo');
        state.items = demoItems;
        renderTable();
        statusBadge.textContent = 'Loaded';
    } catch (error) {
        statusBadge.textContent = 'Error';
        console.error(error);
    }
}

async function updateInventory() {
    if (!state.items.length) {
        statusBadge.textContent = 'Empty';
        return;
    }

    try {
        statusBadge.textContent = 'Updating...';
        const updatedItems = await fetchJson('/api/items/update', {
            method: 'POST',
            body: JSON.stringify(state.items)
        });
        state.items = updatedItems;
        renderTable();
        statusBadge.textContent = 'Updated';
    } catch (error) {
        statusBadge.textContent = 'Error';
        console.error(error);
    }
}

itemForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const name = document.getElementById('itemName').value.trim();
    const sellIn = Number(document.getElementById('itemSellIn').value);
    const quality = Number(document.getElementById('itemQuality').value);

    if (!name) {
        return;
    }

    state.items.push({ name, sellIn, quality });
    itemForm.reset();
    document.getElementById('itemSellIn').value = 5;
    document.getElementById('itemQuality').value = 10;
    renderTable();
});

loadDemoBtn.addEventListener('click', loadDemoInventory);
updateBtn.addEventListener('click', updateInventory);

renderTable();
