//package com.wyldersong.saltengine.game;
//
//import java.util.ArrayList;
//
//import static org.lwjgl.glfw.GLFW.*;
//import static org.lwjgl.opengl.GL30.glBindVertexArray;
//
//public class GameTest {
//	private final Window window;
//	private final TickHandler ticker;
//	public ArrayList<Scene> scenes;
//
//	public GameTest(Window window) {
//		this.window = window;
//		this.scenes = new ArrayList<>();
//		this.ticker = new TickHandler();
//	}
//
//	public void start() {
//		while (!glfwWindowShouldClose(this.window.glfwWindow)) {
//			this.window.clear();
//
//			if (this.ticker.deltaTime() >= 0) {
//				this.window.activeScene.update(this.ticker.getFPS());
//			}
//
//			this.ticker.updateFPS();
//
//			this.window.activeScene.bindShaders();
//
//			if (this.window.activeScene.shaderManager.isUsingDefaultShaders) {
//				this.window.activeScene.shaderManager.setDefaultUniforms();
//			}
//
//			for (int i = 0; i < this.window.activeScene.cells.size(); i++) {
//				this.window.activeScene.cells.get(i).draw();
//			}
//
//			glBindVertexArray(0);
//
//			this.window.update();
//			this.ticker.tick();
//
//			this.window.activeScene.unbindShaders();
//		}
//
//		this.cleanup();
//	}
//
//	public void addScene(Scene scene) {
//		this.scenes.add(scene);
//	}
//
//	public void removeScene(int sceneIndex) {
//		this.scenes.remove(sceneIndex);
//	}
//
//	public void setScene(int sceneIndex) throws Exception {
//		if (sceneIndex > this.scenes.size()) {
//			throw new Exception(String.format("Scene with given index %s does not exist", sceneIndex));
//		}
//
//		this.window.activeScene = this.scenes.get(sceneIndex);
//		this.window.activeScene.init(this.window);
//		this.window.cameraMatrix = this.window.activeScene.camera.projection;
//	}
//
//	public void cleanup() {
//		this.window.cleanup();
//	}
//}
