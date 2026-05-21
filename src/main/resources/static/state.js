const playGrid = document.getElementById("play-grid");
const unitName = document.getElementById("unit-name");

let selectedUnit = null;
let selectedUnitData = null;
let selectedCity = null;
let moveMode = false;
const pendingOrders = new Map();
const unitDataMap = new Map();
const terrain = {};
