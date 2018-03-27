///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package group9.screens;
//
///**
// *
// * @author jonas
// */
//public class GUIPlayScreen {
//    private Stage stage;
//    private Game game;
//    private SpriteBatch batch;
//    private TiledDrawable bottomWall;
//    private TiledDrawable topWall;
//    private TiledDrawable leftWall;
//    private TiledDrawable rightWall;
//    private TiledDrawable centerWall;
//    private TiledDrawable wallBottomLeftCorner;
//    private TiledDrawable wallTopLeftCorner;
//    private TiledDrawable wallBottomRightCorner;
//    private TiledDrawable wallTopRightCorner;
//    private TextureAtlas lava;
//    private TextureAtlas walls;
//    private Animation animation;
//    private float time;
//    
//    public GUIPlayScreen(){
//    //Gdx.app.log("CHECK HEREEEERERERER", Gdx.files.classpath("sprites\\lava\\lava.pack").file().getAbsolutePath());
//        stage = new Stage(new ScreenViewport());
//        lava = new TextureAtlas(Gdx.files.classpath("assets/sprites/lava.pack"));
//        walls = new TextureAtlas(Gdx.files.classpath("assets/sprites/memoryleak.pack"));
//        animation = new Animation(1/11f, lava.findRegions("lava"), PlayMode.LOOP);
//        
//        batch = new SpriteBatch();
//        game = aGame;
//        stage = new Stage(new ScreenViewport());
//        time = 0f;
//        bottomWall = new TiledDrawable(walls.findRegion("wall_bottom"));
//        topWall = new TiledDrawable(walls.findRegion("wall_top"));
//        leftWall = new TiledDrawable(walls.findRegion("wall_left"));
//        rightWall = new TiledDrawable(walls.findRegion("wall_right"));
//        centerWall = new TiledDrawable(walls.findRegion("wall_center"));
//        wallBottomLeftCorner = new TiledDrawable(walls.findRegion("wall_bottom_left"));
//        wallTopLeftCorner = new TiledDrawable(walls.findRegion("wall_top_left"));
//        wallBottomRightCorner = new TiledDrawable(walls.findRegion("wall_bottom_right"));
//        wallTopRightCorner = new TiledDrawable(walls.findRegion("wall_top_right"));
//        
//    }
//
//    @Override
//    public void show() {
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void render(float f) {
//        time += f;
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
//        TextureRegion textureRegion = animation.getKeyFrame(time, true);
//        batch.begin();
//        for(int x = 0; x<960; x += 124){
//            for(int y = 0; y<540; y += 124){
//                batch.draw(textureRegion, x, y);
//            }
//        }
//        centerWall.draw(batch, 80, 64, 800, 412); //done
//        bottomWall.draw(batch, 80, 32, 800, 32); //done
//        topWall.draw(batch, 80, 476, 800, 32); //done
//        leftWall.draw(batch, 48, 64, 32, 412); //done
//        rightWall.draw(batch, 864, 64, 32, 416); 
//        
//        wallBottomLeftCorner.draw(batch, 48, 32, 32, 32); //done
//        wallTopLeftCorner.draw(batch, 48, 476, 32, 32); //done
//        wallBottomRightCorner.draw(batch, 864, 32, 32, 32); //done
//        wallTopRightCorner.draw(batch, 864, 476, 32, 32); //done
//        
//        batch.end();
//        stage.act();
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int i, int i1) {
//    }
//
//    @Override
//    public void pause() {
//    }
//
//    @Override
//    public void resume() {
//    }
//
//    @Override
//    public void hide() {
//    }
//
//    @Override
//    public void dispose() {
//        stage.dispose();
//    }
//    
//    
//}

