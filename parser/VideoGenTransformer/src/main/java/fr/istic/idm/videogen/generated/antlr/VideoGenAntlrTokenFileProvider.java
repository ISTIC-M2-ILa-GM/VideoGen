/*
 * generated by Xtext 2.15.0
 */
package fr.istic.idm.videogen.generated.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class VideoGenAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream("fr/istic/InternalVideoGen.tokens");
	}
}
