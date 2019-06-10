let scene, camera, cameraCtrl, renderer;
let light, light1, light2, light3, light4;
let width, height, cx, cy;
const TMath = THREE.Math;

let conf = {
  xyCoef: 12,
  zCoef: 30,
  lightIntensity: 0.5,
  ambientColor: 0x000000,
  light1Color: chroma.random().hex(),
  light2Color: chroma.random().hex(),
  light3Color: chroma.random().hex(),
  light4Color: chroma.random().hex() };


let plane;

let mouseOver = false;
const mouse = new THREE.Vector2();
const mousePlane = new THREE.Plane(new THREE.Vector3(0, 0, 1), 0);
const mousePosition = new THREE.Vector3();
const raycaster = new THREE.Raycaster();

function init() {
  renderer = new THREE.WebGLRenderer({ antialias: true });
  renderer.setSize(window.innerWidth, window.innerHeight);
  document.body.appendChild(renderer.domElement);

  new p5();
  initScene();
  initGui();
  initEventHandlers();

  animate();
};

function initScene() {
  scene = new THREE.Scene();
  scene.background = new THREE.Color(0x0);
  camera = new THREE.PerspectiveCamera(80, window.innerWidth / window.innerHeight, 0.1, 1000);
  camera.position.z = 100;
  cameraCtrl = new THREE.OrbitControls(camera);
  // cameraCtrl.enableRotate = false;
  cameraCtrl.enableKeys = false;

  initLights();
  initObjects();
}

function initLights() {
  const r = 5;
  const lightIntensity = 0.7;
  const lightDistance = 300;

  light = new THREE.AmbientLight(conf.ambientColor);
  scene.add(light);

  // let color = chroma('#2080D0');
  light1 = new THREE.PointLight(conf.light1Color, lightIntensity, lightDistance);
  light1.position.set(0, r, 20);
  scene.add(light1);
  light2 = new THREE.PointLight(conf.light2Color, lightIntensity, lightDistance);
  light2.position.set(0, -r, 20);
  scene.add(light2);
  light3 = new THREE.PointLight(conf.light3Color, lightIntensity, lightDistance);
  light3.position.set(r, 0, 20);
  scene.add(light3);
  light4 = new THREE.PointLight(conf.light4Color, lightIntensity, lightDistance);
  light4.position.set(-r, 0, 20);
  scene.add(light4);
}

function initObjects() {
  const size = getRendererSize();
  // let mat = new THREE.MeshStandardMaterial({ color: 0xffffff, roughness: 1, metalness: 0 });
  let mat = new THREE.MeshLambertMaterial({ color: 0xffffff });
  let geo = new THREE.PlaneBufferGeometry(size[0], size[1], Math.round(size[0] / 2), Math.round(size[1] / 2));
  plane = new THREE.Mesh(geo, mat);
  scene.add(plane);
}

function initGui() {
  const gui = new dat.GUI();
  gui.close();

  gui.addColor(conf, 'ambientColor').onChange(value => {light.color = new THREE.Color(value);});
  gui.addColor(conf, 'light1Color').onChange(value => {light1.color = new THREE.Color(value);});
  gui.addColor(conf, 'light2Color').onChange(value => {light2.color = new THREE.Color(value);});
  gui.addColor(conf, 'light3Color').onChange(value => {light3.color = new THREE.Color(value);});
  gui.addColor(conf, 'light4Color').onChange(value => {light4.color = new THREE.Color(value);});
  gui.add(conf, 'lightIntensity', 0.1, 2).onChange(value => {
    light1.intensity = value;
    light2.intensity = value;
    light3.intensity = value;
    light4.intensity = value;
  });
  gui.add(conf, 'xyCoef', 1, 50);
  gui.add(conf, 'zCoef', 5, 50);
}

function getRendererSize() {
  mouse.x = 1;mouse.y = 1;
  raycaster.setFromCamera(mouse, camera);
  raycaster.ray.intersectPlane(new THREE.Plane(new THREE.Vector3(0, 0, 1), 0), mousePosition);
  return [mousePosition.x * 2, mousePosition.y * 2];
}

function initEventHandlers() {
  onWindowResize();
  window.addEventListener('resize', onWindowResize, false);

  document.addEventListener('mousemove', e => {
    const v = new THREE.Vector3();
    camera.getWorldDirection(v);
    v.normalize();
    mousePlane.normal = v;

    mouseOver = true;
    mouse.x = e.clientX / width * 2 - 1;
    mouse.y = -(e.clientY / height) * 2 + 1;

    raycaster.setFromCamera(mouse, camera);
    raycaster.ray.intersectPlane(mousePlane, mousePosition);
  });
}

function animate() {
  requestAnimationFrame(animate);

  animatePlane();
  // light1.position.x = mousePosition.x;
  // light1.position.y = mousePosition.y;

  cameraCtrl.update();
  renderer.render(scene, camera);
};

function animatePlane() {
  gArray = plane.geometry.attributes.position.array;
  const time = Date.now() * 0.0002;
  for (let i = 0; i < gArray.length; i += 3) {
    gArray[i + 2] = noise(gArray[i] / conf.xyCoef + time, gArray[i + 1] / conf.xyCoef + time, time + (mouse.x + mouse.y)) * conf.zCoef;
  }
  plane.geometry.attributes.position.needsUpdate = true;
  // plane.geometry.computeBoundingSphere();
}

function onWindowResize() {
  width = window.innerWidth;cx = width / 2;
  height = window.innerHeight;cy = height / 2;
  camera.aspect = width / height;
  camera.updateProjectionMatrix();
  renderer.setSize(width, height);
}

function rnd(max, negative) {
  return negative ? Math.random() * 2 * max - max : Math.random() * max;
}

init();