package io.iridium.closeinventorywithmouse;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CloseInventoryWithMouse.MOD_ID)
public class CloseInventoryWithMouse {

    public static final String MOD_ID = "closeinventorywithmouse";

    public CloseInventoryWithMouse() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }


}

@Mod.EventBusSubscriber
class CloseInventoryManager {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.MouseInputEvent event){

        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null){
            return;
        }

        //1 = keyDown, 0 = keyUp, 2 = keyRepeat
        if(!isInventoryOpen() || event.getAction() != 1){
            return;
        }


        int eventKey = event.getButton();

        KeyMapping inventoryKeybind = Minecraft.getInstance().options.keyInventory;

        int boundKey = inventoryKeybind.getKey().getValue();

        if(eventKey == boundKey){
            player.closeContainer();
        }


    }



    public static boolean isInventoryOpen(){
        Screen screen = Minecraft.getInstance().screen;
        return screen instanceof AbstractContainerScreen;

    }


}
