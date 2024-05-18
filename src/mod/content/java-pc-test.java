package mod.content;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class java-pc-test extends Mod{
    //walls - erekir
      graphiteWall, graphiteWallLarge
    ;

    public java-pc-test(){
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("hello").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("example-java-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some example content.");

        int wallHealthMultiplier = 4;

        graphiteWall = new Wall ("graphite-wall"){{
            requirements(Category.defense, new ItemStack [] { new ItemStack(Items.graphite, 6)});
            health= 130 * wallHealthMultiplier;
            researchCostMultiplier: 0.1f;
                envDisabled |= Env.scorching;
        }};
        graphiteWallLarge= new Wall ("graphite-wall-large"){{
            requirements(Category.defense, new ItemStack [] { new ItemStack(Items.graphite, 24)});
            health= 130 * wallHealthMultiplier * 4;
            researchCostMultiplier: 0.1f;
                envDisabled |= Env.scorching;
        }};
                death = new ItemTurret("death"){{
            requirements(Category.turret, with(Items.copper, 100, Items.graphite, 80, Items.titanium, 50));
            ammo(
                Items.copper,  new BasicBulletType(2.5f, 11){{
                    width = 7f;
                    height = 9f;
                    lifetime = 900f;
                    ammoMultiplier =500;
                }},
            );

            size = 10;
            range = 1000f;
            reload = 31f;
            consumeAmmoOnce = false;
            ammoEjectBack = 3f;
            recoil = 0f;
            shake = 1f;
            shoot.shots = 49;
            shoot.shotDelay = 3f;

            ammoUseEffect = Fx.casing2;
            scaledHealth = 2400;
            shootSound = Sounds.shootBig;

            limitRange();
            coolant = consumeCoolant(2f);
        }};
                
    }

}
